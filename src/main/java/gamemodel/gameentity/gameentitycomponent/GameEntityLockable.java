package gamemodel.gameentity.gameentitycomponent;

import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;
import utilext.ObjectsExt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameEntityLockable extends _GameEntityComponent
{
    final GameEntityMgr.RootNames name;
    IRect calculatedRect;
    private _GameEntityComponent parent;
    private _GameEntityComponent component;

    private boolean isPositionLocked = false;

    public GameEntityLockable(GameEntityMgr.RootNames name, _GameEntityComponent component) {
        super(name);
        this.component = component;
        this.name = name;
    }

    @Override
    public void init() {
        component.init();
        this.calculatedRect = calcRect();
    }

    @Override
    public void update() {
        component.update();

        //_update cached rect
        if(!isPositionLocked) {
            this.calculatedRect = calcRect();
        }
    }

    public void lockPosition() {
        this.isPositionLocked = true;
    }

    @Override
    public void setUpdateAction(_GameEntityActionChain action) {
        if(this.component != null) {
            this.component.setUpdateAction(action);
        }
    }

    @Override
    public void setSpeed(int speed) { }

    @Override
    public void setSpriteName(SpriteName spriteName) {
        //intentionally not implemented
    }

    @Override
    public _Sprite getSprite() {
        //intentionally not implemented
        return null;
    }

    @Override
    public void setSprite(_Sprite sprite) {
        //intentionally not implemented
    }

    public void add(_GameEntityComponent component) {
        this.component.add(component);
        component.setParent(this);
    }

    @Override
    public IRect getRect() {
        return this.calculatedRect;
    }

    private IRect calcRect() {
        return component.getRect();
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
        if(component != null) {
            var l = new ArrayList<_GameEntityComponent>(1);
            l.add(component);
            return l;

        }
        else {
            return super.getChildren();
        }
    }

    @Override
    public void translatePosition(double x, double y) {
        if(!isPositionLocked) {
            component.translatePosition(x, y);
            calcRect();
        }
    }

    @Override
    public void setParent(_GameEntityComponent gameEntity) {
        ObjectsExt.requireNotEquals(this.parent, gameEntity);
        this.parent = gameEntity;
    }

    @Override
    public void detach(_GameEntityComponent gameEntity) {
        Objects.requireNonNull(gameEntity);
        this.component = null;
        detachFromParent();
    }

    @Override
    public void detachFromParent() {
        this.parent.detach(this);
    }

    @Override
    public _GameEntityActionChain getHitAction() {
        return null;
    }

    @Override
    public _GameEntityActionChain getDestroyAction() {
        return null;
    }
}
