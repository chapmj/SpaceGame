package gamemodel.gameentity;

import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

import java.util.Objects;

public class GameEntity implements IGameEntity, IUpdateable, ICollidable, IDestroyable {
    _GameEntityActionChain updateAction;
    _GameEntityActionChain hitAction;
    _GameEntityActionChain destroyAction;
    GameEntityMgr.RootNames rootName;

    _Sprite sprite;
    SpriteName spriteName;
    IRect rect;
    int speed;

    public GameEntity(IRect rect, GameEntityMgr.RootNames rootName) {
        Objects.requireNonNull(rect);
        this.rect = rect;
        this.rootName = rootName;
        this.speed = 0;
        this.spriteName = SpriteName.DEFAULT;
    }

    @Override
    public IRect getRect() {
        return rect;
    }

    @Override
    public void swapRect(IRect rect) {
        Objects.requireNonNull(rect);
        this.rect = rect;
    }

    public GameEntityMgr.RootNames getName() {
        return this.rootName;
    }

    @Override
    public void setName(GameEntityMgr.RootNames rootName) {
        this.rootName = rootName;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void update() {
        if(updateAction != null) {
            updateAction.doAction(this);
        }
    }

    public void setUpdateAction(_GameEntityActionChain action) {
        this.updateAction = action;
    }

    public _GameEntityActionChain getUpdateAction() {
        return this.updateAction;
    }

    @Override
    public void collide(IGameEntity gameEntity) {
        if(hitAction != null) {
            hitAction.doAction(gameEntity);
        }
    }

    @Override
    public void setHitAction(_GameEntityActionChain action) {
        this.hitAction = action;
    }

    @Override
    public void addHitAction(_GameEntityActionChain action) {
        if(this.hitAction == null) {
            this.hitAction = action;
        }
        else {
            this.hitAction.addAction(action);
        }
    }


    @Override
    public _GameEntityActionChain getHitAction() {
        return hitAction;
    }

    @Override
    public void destroy() {
        Objects.requireNonNull(this.destroyAction);
        this.destroyAction.doAction(this);
    }

    public void setDestroyAction(_GameEntityActionChain action) {
        this.destroyAction = action;
    }

    @Override
    public _GameEntityActionChain getDestroyAction() {
        return null;
    }

    public SpriteName getSpriteName() {
        return spriteName;
    }

    public void setSpriteName(SpriteName spriteName) {
        this.spriteName = spriteName;
    }

    @Override
    public _Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public void setSprite(_Sprite sprite) {
        this.sprite = sprite;
    }

}
