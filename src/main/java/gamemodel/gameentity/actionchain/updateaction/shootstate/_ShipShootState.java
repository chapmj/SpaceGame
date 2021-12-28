package gamemodel.gameentity.actionchain.updateaction.shootstate;

import gamemodel.gameentity.IGameEntity;

public abstract class _ShipShootState {
    public static _ShipShootState FIRING = new ShipShootFiringState();
    public static _ShipShootState STOPPED = new ShipShootStoppedState();

    public abstract void update(IGameEntity gameEntity);
    public abstract void dump();

}
