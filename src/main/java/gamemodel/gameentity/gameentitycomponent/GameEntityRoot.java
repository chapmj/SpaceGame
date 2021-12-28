package gamemodel.gameentity.gameentitycomponent;

import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.FactoryRectangle;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class GameEntityRoot extends _GameEntityComponent {

    private _GameEntityComponent gameEntityComponent;

    public GameEntityRoot(GameEntityMgr.RootNames name) {
        super(name);
        this.gameEntityComponent = new GameEntityComposite(name);
    }

    public _GameEntityComponent getGameEntityComponent() {
        return gameEntityComponent;
    }

    public List<_GameEntityComponent> getChildren() {
        var l = new ArrayList<_GameEntityComponent>();
        l.add(gameEntityComponent);
        return l;
    }

    @Override
    public void swapRect(IRect rect) {
        try {
            throw new ExecutionControl.NotImplementedException("not implemented");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSpeed() {
        if(gameEntityComponent != null) {
            return gameEntityComponent.getSpeed();
        }
        else return 0;
    }

    @Override
    public void setSpeed(int speed) {
        if(gameEntityComponent != null) {
            gameEntityComponent.setSpeed(speed);
        }
    }

    @Override
    public void setSpriteName(SpriteName spriteName) {
        try {
            throw new ExecutionControl.NotImplementedException("not implemented");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public _Sprite getSprite() {
        return this.gameEntityComponent.getSprite();
    }

    @Override
    public void setSprite(_Sprite sprite) {
        //intentionally blank
    }

    @Override
    public void add(_GameEntityComponent gameEntityComponent) {
        this.gameEntityComponent.add(gameEntityComponent);
        if(this.gameEntityComponent == gameEntityComponent) {
            gameEntityComponent.setParent(null);
        }
        else {
            this.gameEntityComponent.setParent(gameEntityComponent);
        }
    }

    @Override
    public void update() {
        if(gameEntityComponent != null) {
            gameEntityComponent.update();
        }
    }

    @Override
    public void setUpdateAction(_GameEntityActionChain action) {
        if(gameEntityComponent != null) {
            gameEntityComponent.setUpdateAction(action);
        }
    }

    @Override
    public IRect getRect() {

        if(gameEntityComponent != null) {
            return gameEntityComponent.getRect();
        }
        else return FactoryRectangle.getNull();
    }

    @Override
    public void setParent(_GameEntityComponent gameEntity) {
        gameEntity.setParent(this);
    }

    @Override
    public void detach(_GameEntityComponent gameEntityComponent) {
        this.gameEntityComponent = null;
    }

    @Override
    public void setDestroyAction(_GameEntityActionChain action) {
        this.gameEntityComponent.destroy();
    }

    @Override
    public _GameEntityActionChain getDestroyAction() {
            if(gameEntityComponent != null) {
                return gameEntityComponent.getDestroyAction();
            }
            else {
                return null;
            }
        }

    @Override
    public _GameEntityActionChain getHitAction() {
        return this.gameEntityComponent.getHitAction();
    }
}
