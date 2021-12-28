package gamemodel.gameentity.factory;

import gamemodel.gameentity.IGameEntity;

public interface IFactoryGameEntity {
    IGameEntity create();
    IGameEntity create(double x, double y, double w, double h);
}
