package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public class MoveUpdateAction extends _MoveUpdateAction {


    public MoveUpdateAction(_MoveState moveState) {
        super(moveState);
    }

    public void _doAction(IGameEntity gameEntity) {
        currMoveState.update(gameEntity);
        if(currMoveState == _MoveState.MOVE_SOUTH_FIXED) {
            nextState();
        }
    }

    public void nextState() {
        currMoveState = _MoveState.STOPPED;
    }

    public void setState(_MoveState moveState) {
        this.currMoveState = moveState;
    }

    public _MoveState getState() {
        return this.currMoveState;
    }
}
