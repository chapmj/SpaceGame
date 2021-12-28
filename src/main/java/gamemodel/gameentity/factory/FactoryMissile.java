package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.NullDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.*;
import gamemodel.gameentity.actionchain.updateaction.movestate.MoveUpdateAction;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

import java.util.Objects;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.*;

public class FactoryMissile extends _FactoryGameEntity {
    private static FactoryMissile instance;

    public static FactoryMissile getInstance() {
        if (instance == null) {
            instance = new FactoryMissile();
        }
        return instance;
    }

    @Override
    public IGameEntity create() {
        //var sprite = SpriteMgr.getInstance().get(SpriteName.MISSILE);
        var spriteMgr = SpriteMgr.getInstance();
        var sprite = SpriteMgr.getInstance().create(SpriteName.MISSILE);
        spriteMgr.add(sprite);

        var w = sprite.getW();
        var h = sprite.getH();
        var name = MISSILE_ROOT;
        var speed = 11;

        var missileGameEntity = FactoryGameEntity.Create(w, h, speed, name);
        var spriteSubscriber = new GameEntitySpriteSubscriber(missileGameEntity);
        sprite.attach(spriteSubscriber);

        configureUpdate(missileGameEntity);
        configureHit(missileGameEntity, sprite, spriteSubscriber);
        configureDestroy(missileGameEntity);

        return missileGameEntity;
    }

    private void configureUpdate(IGameEntity missileGameEntity) {
        Objects.requireNonNull(missileGameEntity);
        var moveAction = new MoveUpdateAction(_MoveState.MOVE_NORTH);
        missileGameEntity.setUpdateAction(moveAction);
    }

    private void configureHit(IGameEntity missileGameEntity, _Sprite sprite, GameEntitySpriteSubscriber spriteSubscriber) {
        Objects.requireNonNull(missileGameEntity);
        var hitAction = new ChoiceAction(missileGameEntity);

        hitAction.add(ALIEN_ROOT, new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.add(ALIEN_ROOT, new RemoveGameEntityHitAction(MISSILE_ROOT, missileGameEntity));
        hitAction.add(ALIEN_ROOT, new RemoveCollisionHitAction(missileGameEntity));
        hitAction.add(ALIEN_ROOT, new TimedSetMissileAvailableHitAction());

        hitAction.add(WALL_ROOT, new CreateTimedSplatHitAction(missileGameEntity, SpriteName.MISSILE_SPLAT, (char)30));
        hitAction.add(WALL_ROOT, new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.add(WALL_ROOT, new TimedSetMissileAvailableHitAction());
        hitAction.add(WALL_ROOT, new RemoveGameEntityHitAction(MISSILE_ROOT, missileGameEntity));
        hitAction.add(WALL_ROOT, new RemoveCollisionHitAction(missileGameEntity));

        hitAction.add(UFO_ROOT, new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.add(UFO_ROOT, new TimedSetMissileAvailableHitAction());
        hitAction.add(UFO_ROOT, new RemoveGameEntityHitAction(MISSILE_ROOT, missileGameEntity));
        hitAction.add(UFO_ROOT, new RemoveCollisionHitAction(missileGameEntity));

        hitAction.add(SHIELD_ROOT, new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.add(SHIELD_ROOT, new RemoveGameEntityHitAction(MISSILE_ROOT, missileGameEntity));
        hitAction.add(SHIELD_ROOT, new RemoveCollisionHitAction(missileGameEntity));
        hitAction.add(SHIELD_ROOT, new TimedSetMissileAvailableHitAction());
        hitAction.add(SHIELD_ROOT, new CreatePermaSplatCentered(missileGameEntity));

        hitAction.add(BOMB_ROOT, new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        hitAction.add(BOMB_ROOT, new RemoveGameEntityHitAction(MISSILE_ROOT, missileGameEntity));
        hitAction.add(BOMB_ROOT, new RemoveCollisionHitAction(missileGameEntity));
        hitAction.add(BOMB_ROOT, new TimedSetMissileAvailableHitAction());

        missileGameEntity.setHitAction(hitAction);

    }

    private void configureDestroy(IGameEntity missileGameEntity) {
        Objects.requireNonNull(missileGameEntity);
        missileGameEntity.setDestroyAction(new NullDestroyAction());

    }
}
