package gamemodel.gameentity.gameentitycomponent;

import utilext.CompositePatternException;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

public class GameEntityLeaf extends _GameEntityComponent
{
    protected final IGameEntity gameEntity;
    private _GameEntityComponent parent;

    public GameEntityLeaf(IGameEntity gameEntity) {
        super(gameEntity.getName());
        this.gameEntity = gameEntity;
    }

    @Override
    public void update() {
        gameEntity.update();
    }

    @Override
    public void setUpdateAction(_GameEntityActionChain action) {
        gameEntity.setUpdateAction(action);
    }

    @Override
    public void setSpeed(int speed) { }

    @Override
    public void setSpriteName(SpriteName spriteName) {
        //intentionally not implemented
    }

    @Override
    public _Sprite getSprite() {
        //return null;
        //intentionally not implemented
        return gameEntity.getSprite();
    }

    @Override
    public void setSprite(_Sprite sprite) {
        //intentionally not implemented
    }

    public void add(_GameEntityComponent component) {
        try {
            throw new CompositePatternException("Cannot add component to leaf node.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IRect getRect() {
        return gameEntity.getRect();
    }

    @Override
    public GameEntityMgr.RootNames getName() {
        return this.gameEntity.getName();
    }

    @Override
    public void setName(GameEntityMgr.RootNames name) {
        this.gameEntity.setName(name);
    }

    @Override
    public void swapRect(IRect rect) {
        gameEntity.swapRect(rect);
        gameEntity.update();
    }

    @Override
    public int getSpeed() {

        try {
            throw new CompositePatternException("functionality not defined");
        }
        catch (CompositePatternException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void collide(IGameEntity gameEntity) {
        this.gameEntity.collide(gameEntity);
    }

    @Override
    public void setHitAction(_GameEntityActionChain command) {
        this.gameEntity.setHitAction(command);
    }

    @Override
    public void addHitAction(_GameEntityActionChain command) {
        this.gameEntity.setHitAction(command);
    }

    @Override
    public _GameEntityActionChain getHitAction() {
        return this.gameEntity.getHitAction();
    }

    public boolean matches(IGameEntity gameEntity) {
        return gameEntity == this.gameEntity;
    }

    public void setParent(_GameEntityComponent parent) {
        this.parent = parent;
    }

    public void detachFromParent() {
        if(parent != null) {
            this.parent.detach(this);
            this.parent = null;
        }
    }

    @Override
    public void setDestroyAction(_GameEntityActionChain action) {
        gameEntity.setDestroyAction(action);
    }

    @Override
    public _GameEntityActionChain getDestroyAction() {
        return null;
    }

}
