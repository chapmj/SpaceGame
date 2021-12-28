package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntity;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gamerect.FactoryRectangle;

public class FactoryGameEntity extends _FactoryGameEntity {

    static FactoryGameEntity instance;

    private FactoryGameEntity() {}

    public static FactoryGameEntity getInstance() {

        if(instance == null) {
            instance = new FactoryGameEntity();
        }
        return instance;
    }

    public static IGameEntity Create(double w, double h, int speed, GameEntityMgr.RootNames name) {
        var gameEntity = getInstance().create();
        gameEntity.getRect().setWidth(w);
        gameEntity.getRect().setHeight(h);
        gameEntity.setSpeed(speed);
        gameEntity.setName(name);
        return gameEntity;
    }


    public IGameEntity create() {
        var rect = FactoryRectangle.create(0,0);
        var gameEntity = (IGameEntity) new GameEntity(rect, GameEntityMgr.RootNames.DEFAULT);
        return gameEntity;
    }


}
