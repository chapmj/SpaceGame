package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

import java.util.Objects;

public abstract class _MoveUpdateAction extends _GameEntityActionChain {

    protected _MoveState currMoveState;

    public _MoveUpdateAction(_MoveState moveState) {
        Objects.requireNonNull(moveState);
        this.currMoveState = moveState;
    }

    public void _doAction(IGameEntity gameEntity) {

    }

    public abstract void nextState();
    public abstract void setState(_MoveState moveState);
    public abstract _MoveState getState();
}
