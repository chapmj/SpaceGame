package gamemodel.gameentity.gameentitycomponent;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.IRect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class _GameEntityComponent implements IGameEntity {
    protected static List<_GameEntityComponent> emptyList = Collections.unmodifiableList(new ArrayList<>(0));
    protected final GameEntityMgr.RootNames name;

    protected _GameEntityComponent(GameEntityMgr.RootNames name) {
        this.name = name;
    }

    public abstract void add(_GameEntityComponent _GameEntityComponent);

    public void init() { }

    public abstract void update();

    public abstract IRect getRect();

    public void swapRect(IRect rect) { }

    public List<_GameEntityComponent> getChildren() {
        return emptyList;
    }

    public void setName(GameEntityMgr.RootNames rootName) { }

    public GameEntityMgr.RootNames getName() {
        return this.name;
    }

    public void translatePosition(double x, double y) {
        this.getRect().translate(x, y);
    }

    @Override
    public void collide(IGameEntity gameEntity) {
        //not implemented
    }

    @Override
    public void setHitAction(_GameEntityActionChain command) {
        //not implemented
    }

    @Override
    public void addHitAction(_GameEntityActionChain command) {
        //not implemented
    }

    public abstract void setParent(_GameEntityComponent gameEntity);

    public boolean matches(IGameEntity gameEntity) {
        return false;
    }

    protected void detach(_GameEntityComponent gameEntity) {

    }

    public void detachFromParent() {

    }

    public void destroy() {

    }

    public void setDestroyAction(_GameEntityActionChain action) {
        //not implemented
    }

}
