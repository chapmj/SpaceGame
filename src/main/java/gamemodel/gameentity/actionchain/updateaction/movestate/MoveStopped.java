package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public class MoveStopped extends _MoveState {
    public MoveStopped() {
    }

    @Override
    public void update(IGameEntity gameEntity) {

    }

    @Override
    public void dump() {
        System.out.println("Stopped");
    }


}
