package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

import java.util.Objects;

public class MoveStateHolder extends _MoveState {
    _MoveState moveState;

    public MoveStateHolder() {
        this.moveState = _MoveState.STOPPED;
    }

    public void setMoveState(_MoveState moveState) {
        Objects.requireNonNull(moveState);
        this.moveState = moveState;
    }

    public _MoveState getMoveState() {
        return this.moveState;
    }

    @Override
    public void update(IGameEntity gameEntity) {
        moveState.update(gameEntity);
    }

}
