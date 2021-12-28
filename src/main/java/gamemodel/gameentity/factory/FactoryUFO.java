package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.FadeOutMediaDestroyAction;
import gamemodel.gameentity.actionchain.destroyaction.UFODestroyAction;
import gamemodel.gameentity.actionchain.hitaction.*;
import gamemodel.gameentity.actionchain.updateaction.PlayMediaUpdateAction;
import gamemodel.gameentity.actionchain.updateaction.movestate.MoveUpdateAction;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteName;
import gameview.gameimage.SpriteMgr;
import gameview.sound.MediaMgr;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.MISSILE_ROOT;

public class FactoryUFO extends _FactoryGameEntity {
    private static FactoryUFO instance;
    private _MoveState moveDirection = _MoveState.MOVE_EAST;

    public static FactoryUFO getInstance() {
        if (instance == null) {
            instance = new FactoryUFO();
        }
        return instance;
    }


    public void setMoveDirection(_MoveState direction) {
        this.moveDirection = direction;
    }


    public  IGameEntity create() {
        var sprite = SpriteMgr.getInstance().create(SpriteName.UFO);
        SpriteMgr.getInstance().add(sprite);


        var w = sprite.getW();
        var h = sprite.getH();
        var name = GameEntityMgr.RootNames.UFO_ROOT;
        var speed = 2;

        var gameEntity = FactoryGameEntity.Create(w, h, speed, name);
        var spriteSubscriber = new GameEntitySpriteSubscriber(gameEntity);

        sprite.attach(spriteSubscriber);

        var updateAction = new MoveUpdateAction(this.moveDirection);
        updateAction.addAction(new PlayMediaUpdateAction(MediaMgr.SoundName.UFO_BUZZ));
        gameEntity.setUpdateAction(updateAction);

        var hitAction = new ChoiceAction(gameEntity);
        //hitAction.add(MISSILE_ROOT, new PlaySoundHitAction(SoundMgr.SoundName.MISSILE_EXPLODE));
        hitAction.add(MISSILE_ROOT, new CreateTimedSplatHitAction(gameEntity, SpriteName.UFO_CRASH, (char) 60));
        hitAction.add(MISSILE_ROOT, new UFODeployMgrHitAction());
        hitAction.add(MISSILE_ROOT, new DestroyGameEntityHitAction(gameEntity));
        hitAction.add(MISSILE_ROOT, new StopMediaHitAction(MediaMgr.SoundName.UFO_BUZZ));
        hitAction.add(MISSILE_ROOT, new UpdateUFOScoreHitAction());
        hitAction.add(MISSILE_ROOT, new PlayMediaUpdateAction(MediaMgr.SoundName.UFO_CRASH));
        gameEntity.setHitAction(hitAction);

        var destroyAction = new UnsubscribeSpriteAction(sprite, spriteSubscriber);
        destroyAction.addAction(new UnsubscribeSpriteAction(sprite, spriteSubscriber));
        destroyAction.addAction(new UFODestroyAction());
        destroyAction.addAction(new FadeOutMediaDestroyAction(MediaMgr.SoundName.UFO_BUZZ));
        gameEntity.setDestroyAction(destroyAction);

        return gameEntity;
    }

}
