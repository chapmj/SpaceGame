package gamemodel.gameentity.factory;

import gamecontroller.command.AnimateIntervalSpriteCommand;
import gamecontroller.command.CreateDurationSplatCenteredCmd;
import gamecontroller.command.CreatePermaSplatCenteredCmd;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.NullDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.*;
import gamemodel.gameentity.actionchain.updateaction.movestate.MoveUpdateAction;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;

import java.util.Random;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.*;

public class FactoryBomb extends _FactoryGameEntity {
    private static FactoryBomb instance;
    private Random random;

    public static FactoryBomb getInstance() {
        if (instance == null) {
            instance = new FactoryBomb();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.random = new Random();

    }

    @Override
    public IGameEntity create() {
        var randomBomb = random.nextInt(3);
        SpriteName spriteName = switch (randomBomb) {
            case 0 -> SpriteName.BOMB_TUMBLE;
            case 1 -> SpriteName.BOMB_ZIGZAG;
            case 2 -> SpriteName.BOMB_DRILL;
            default -> SpriteName.MISSILE;
        };

        var sprite = SpriteMgr.getInstance().create(spriteName);
        SpriteMgr.getInstance().add(sprite);

        var w = sprite.getW();
        var h = sprite.getH();
        var name = GameEntityMgr.RootNames.BOMB_ROOT;
        var speed = 4;
        var bombGameEntity = FactoryGameEntity.Create(w, h, speed, name);

        var spriteSubscriber = new GameEntitySpriteSubscriber(bombGameEntity);
        sprite.attach(spriteSubscriber);

        var animateCmd = new AnimateIntervalSpriteCommand(sprite, 11, GameTime.getInstance());

        var updateAction = new MoveUpdateAction(_MoveState.MOVE_SOUTH);
        updateAction.addAction(new CommandExecutorAction(animateCmd));
        bombGameEntity.setUpdateAction(updateAction);


        var hitAction = new ChoiceAction(bombGameEntity);
        hitAction.add(ALIEN_ROOT, new CreateTimedSplatHitAction(bombGameEntity, SpriteName.MISSILE_SPLAT, (char)30));
        hitAction.add(WALL_ROOT, new CreateTimedSplatHitAction(bombGameEntity, SpriteName.MISSILE_SPLAT, (char)30));
        hitAction.add(SHIELD_ROOT, new CommandExecutorAction(new CreateDurationSplatCenteredCmd(bombGameEntity, SpriteName.MISSILE_SPLAT, 0, 8, (char)30)));
        hitAction.add(SHIELD_ROOT, new TimedCommandExecutorAction(new CreatePermaSplatCenteredCmd(bombGameEntity, SpriteName.SPLAT, 0, 8), 30));
        hitAction.add(MISSILE_ROOT, new CreateTimedSplatHitAction(bombGameEntity, SpriteName.MISSILE_SPLAT, (char)30));

        //do for all
        hitAction.addAction(new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.addAction(new RemoveGameEntityHitAction(BOMB_ROOT, bombGameEntity));
        hitAction.addAction(new RemoveCollisionHitAction(bombGameEntity));




        bombGameEntity.setHitAction(hitAction);

        bombGameEntity.setDestroyAction(new NullDestroyAction());

        return bombGameEntity;
    }
}
