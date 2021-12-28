package gamemodel.gameentity.actionchain.updateaction.shootstate;

import gamemodel.gameentity.IGameEntity;

public class ShipShootStoppedState extends _ShipShootState {
    @Override
    public void update(IGameEntity gameEntity) { }

    @Override
    public void dump() {
        System.out.println("ShootState: STOPPED");
    }
}
