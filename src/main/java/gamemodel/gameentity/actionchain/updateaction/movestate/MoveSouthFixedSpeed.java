package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public class MoveSouthFixedSpeed extends _MoveState {
    int speed;

    public void update(IGameEntity gameEntity) {
        var rect = gameEntity.getRect();
        rect.translate(0, speed);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void dump() {
        System.out.println("MoveSouth");
    }
}
