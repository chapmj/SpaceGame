package gamemodel.gameentity.gameentitycomponent;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;
import utilext.Geometry;
import utilext.ObjectsExt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameEntityComposite extends _GameEntityComponent
{
    final List<_GameEntityComponent> components;
    final GameEntityMgr.RootNames name;
    IRect calculatedRect;
    private _GameEntityComponent parent;
    _GameEntityActionChain localUpdateAction;
    _GameEntityActionChain localDestroyAction;
    _GameEntityActionChain localHitAction;

    public GameEntityComposite(GameEntityMgr.RootNames name) {
        super(name);
        this.components = new ArrayList<>();
        this.name = name;
        this.calculatedRect = calcRect(components);
    }

    @Override
    public void init() {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).init();
        }
        this.calculatedRect = calcRect(this.components);
    }

    @Override
    public void update() {

        //_update all children
        for(int i = 0; i < components.size(); i++) {
            var child = components.get(i);
            if(child != null) {
                child.update();
            }
        }

        //_update cached rect
        this.calculatedRect = calcRect(this.components);

        if(localUpdateAction != null) {
            localUpdateAction.doAction(this);
        }

    }

    @Override
    public void collide(IGameEntity gameEntity) {
        localHitAction._doAction(gameEntity);
    }

    @Override
    public void setUpdateAction(_GameEntityActionChain action) {

        for(int i = 0; i < components.size(); i++) {
            var child = components.get(i);
            if(child != null) {
                child.setUpdateAction(action);
            }
        }
    }

    public void setLocalUpdateAction(_GameEntityActionChain action) {
        this.localUpdateAction = action;
    }

    @Override
    public void destroy() {
        for(int i = 0; i < components.size(); i++) {
            var child = components.get(i);
            if(child != null) {
                child.destroy();
            }
        }

        if(localDestroyAction != null) {
            localDestroyAction.doAction(this);
        }

        localDestroyAction = null;
        localUpdateAction = null;
    }

    @Override
    public void setDestroyAction(_GameEntityActionChain action) {

        for(int i = 0; i < components.size(); i++) {
            var child = components.get(i);
            if(child != null) {
                child.setDestroyAction(action);
            }
        }
    }

    public void setLocalDestroyAction(_GameEntityActionChain action) {
        this.localDestroyAction = action;
    }

    public void setLocalHitAction(_GameEntityActionChain action) {
        this.localHitAction = action;
    }

    @Override
    public _GameEntityActionChain getDestroyAction() {
        return null;
    }

    @Override
    public void setSpeed(int speed) { }

    @Override
    public void setSpriteName(SpriteName spriteName) {
        //intentionally not implemented
    }

    @Override
    public _Sprite getSprite() {
        return null;//replace with default
    }

    @Override
    public void setSprite(_Sprite sprite) {
        //intentionally blank

    }

    public void add(_GameEntityComponent component) {
        this.components.add(component);
        component.setParent(this);
    }

    @Override
    public IRect getRect() {
        return this.calculatedRect;
    }

    private IRect calcRect(List<_GameEntityComponent> components) {

/*
        List<IGameEntity> gameEntities = new ArrayList<>(components);

        return GameEntityUtils.consolidateRects(gameEntities);
*/
        List<IRect> rects = new ArrayList<>();
        for(IGameEntity gameEntity : components) {
            rects.add(gameEntity.getRect());
        }
        return Geometry.mergeRects(rects);
    }

    @Override
    public GameEntityMgr.RootNames getName() {
        return this.name;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public List<_GameEntityComponent> getChildren() {
        return components;
    }

    @Override
    public void translatePosition(double x, double y) {

        for(int i = 0; i < components.size(); i++) {
            var child = components.get(i);
            if(child != null) {
                child.translatePosition(x, y);
            }
        }

        calcRect(this.getChildren());
    }

    @Override
    public void setParent(_GameEntityComponent gameEntity) {
        ObjectsExt.requireNotEquals(this.parent, gameEntity);
        this.parent = gameEntity;
    }

    @Override
    public void detach(_GameEntityComponent gameEntity) {
        Objects.requireNonNull(gameEntity);
        this.components.remove(gameEntity);
        if(components.isEmpty()) {
            detachFromParent();
            destroy();
        }
    }

    @Override
    public void detachFromParent() {
        if (parent != null) {
            this.parent.detach(this);
        }
    }

    @Override
    public _GameEntityActionChain getHitAction() {
        return null;
    }

}
