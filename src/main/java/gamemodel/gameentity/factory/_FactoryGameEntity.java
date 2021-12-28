package gamemodel.gameentity.factory;

import gamemodel.gameentity.IGameEntity;

public abstract class _FactoryGameEntity implements IFactoryGameEntity {

    public abstract IGameEntity create();

    public IGameEntity create(double x, double y, double w, double h) {

        var gameEntity = create();
        var rect = gameEntity.getRect();
        rect.translate(x, y);
        rect.setWidth(w);
        rect.setHeight(h);

        return gameEntity;
    }
}
