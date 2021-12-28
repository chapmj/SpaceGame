package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntity;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gamerect.FactoryRectangle;

public class FactoryWall extends _FactoryGameEntity {

    private static FactoryWall instance;

    private FactoryWall() {}

    public static FactoryWall getInstance() {
        if(instance == null) {
            instance = new FactoryWall();
        }
        return instance;
    }

    @Override
    public IGameEntity create() {
        var rect = FactoryRectangle.create();
        var gameEntity = (IGameEntity) new GameEntity(rect, GameEntityMgr.RootNames.WALL_ROOT);
        return gameEntity;
    }

}
