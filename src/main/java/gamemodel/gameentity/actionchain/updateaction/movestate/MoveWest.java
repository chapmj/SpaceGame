package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public class MoveWest extends _MoveState {
    public void update(IGameEntity gameEntity) {
        var rect = gameEntity.getRect();
        var speed = gameEntity.getSpeed();
        rect.translate(-speed, 0);
    }

    public void dump() {
        System.out.println("MoveWest");
    }
}
