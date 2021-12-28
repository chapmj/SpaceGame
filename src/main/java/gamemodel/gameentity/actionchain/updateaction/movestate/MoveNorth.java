package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public class MoveNorth extends _MoveState {

    public void update(IGameEntity gameEntity) {
        var rect = gameEntity.getRect();
        var speed = gameEntity.getSpeed();
        rect.translate(0, -speed);
    }

    @Override
    public void dump() {
        System.out.println("MoveNorth");
    }
}
