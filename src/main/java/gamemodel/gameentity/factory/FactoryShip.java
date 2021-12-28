package gamemodel.gameentity.factory;

import gamecontroller.GameStats;
import gamecontroller.command.CreateShipCommand;
import gamecontroller.command.DecreaseLivesCommand;
import gamecontroller.command.GameOverCommand;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.gamesystem._GameSystemState;
import gamecontroller.input.KeyboardController;
import gamemodel.gameentity.GameEntity;
import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.NullDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.*;
import gamemodel.gameentity.actionchain.updateaction.KeyUpdateAction;
import gamemodel.gameentity.actionchain.updateaction.ShootUpdateAction;
import gamemodel.gamerect.FactoryRectangle;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import gameview.sound.SoundMgr;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.*;

public class FactoryShip extends _FactoryGameEntity {
    private static FactoryShip instance;

    public static FactoryShip getInstance() {
        if (instance == null) {
            instance = new FactoryShip();
        }
        return instance;
    }

    @Override
    public IGameEntity create() {

        var shipSprite = SpriteMgr.getInstance().create(SpriteName.SHIP);
        SpriteMgr.getInstance().add(shipSprite);

        var rect = FactoryRectangle.create();
        var x = shipSprite.getX();
        var y = shipSprite.getY();
        var w = shipSprite.getW();
        var h = shipSprite.getH();
        rect.setWidth(w);
        rect.setHeight(h);
        rect.positionRect(x, y);

        IGameEntity shipGameEntity = new GameEntity(rect, SHIP_ROOT);

        shipGameEntity.setSpeed(5);

        var spriteSubscriber = new GameEntitySpriteSubscriber(shipGameEntity);
        shipSprite.attach(spriteSubscriber);

        var keyMoveAction = new KeyUpdateAction();
        var keyboard =  KeyboardController.getInstance();
        keyboard.createSubscription(keyMoveAction, 'A');
        keyboard.createSubscription(keyMoveAction, 'D');

        var shootAction = new ShootUpdateAction();
        keyboard.createSubscription(shootAction, ' ');

        var updateAction = keyMoveAction;
        updateAction.addAction(shootAction);
        shipGameEntity.setUpdateAction(updateAction);

        var handleKey = new HandleKeyAction();
        handleKey.setKeyActionReference(keyMoveAction);

        var hitAction = new ChoiceAction(shipGameEntity);
        hitAction.add(WALL_ROOT, handleKey);

        hitAction.add(BOMB_ROOT, new UnsubscribeSpriteAction(shipSprite, spriteSubscriber));
        hitAction.add(BOMB_ROOT, new PlaySoundHitAction(SoundMgr.SoundName.MISSILE_EXPLODE));
        hitAction.add(BOMB_ROOT, new DestroyGameEntityHitAction(shipGameEntity));
        hitAction.add(BOMB_ROOT, new CommandExecutorAction(new DecreaseLivesCommand()));


        var livesAvailable = GameStats.getInstance().getLives();

        if(livesAvailable > 0) {
            hitAction.add(BOMB_ROOT, new TimedCommandExecutorAction(new CreateShipCommand(), 180));
        }

        hitAction.add(BOMB_ROOT, new RemoveGameEntityHitAction(SHIP_ROOT, shipGameEntity));
        hitAction.add(BOMB_ROOT, new RemoveCollisionHitAction(shipGameEntity));
        hitAction.add(BOMB_ROOT, new CreateTimedSplatHitAction(shipGameEntity, SpriteName.SHIP_CRASH, 15, 10));
        hitAction.add(BOMB_ROOT, new PauseMarchHitAction(10));

        hitAction.add(BOMB_ROOT, new CommandExecutorAction(new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE)));
        hitAction.add(BOMB_ROOT, new TimedCommandExecutorAction(new GameUpdateStateChangeCommand(_GameSystemState.SHIP_HIT_STATE), 200));

        hitAction.add(ALIEN_ROOT, new UnsubscribeSpriteAction(shipSprite, spriteSubscriber));
        hitAction.add(ALIEN_ROOT, new PlaySoundHitAction(SoundMgr.SoundName.MISSILE_EXPLODE));
        hitAction.add(ALIEN_ROOT, new CreateTimedSplatHitAction(shipGameEntity, SpriteName.SHIP_CRASH, 15, 10));
        hitAction.add(ALIEN_ROOT, new RemoveGameEntityHitAction(SHIP_ROOT, shipGameEntity));
        hitAction.add(ALIEN_ROOT, new RemoveCollisionHitAction(shipGameEntity));
        hitAction.add(ALIEN_ROOT, new CommandExecutorAction(new GameOverCommand()));

        shipGameEntity.setHitAction(hitAction);

        shipGameEntity.setDestroyAction(new NullDestroyAction());


        return shipGameEntity;
    }

}
