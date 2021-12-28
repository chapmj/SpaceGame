package gamecontroller.command;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

public class MoveCommand extends _GameEntityCommand {

    _MoveState moveState = _MoveState.STOPPED;
    private IGameEntity gameEntity;

    public MoveCommand(IGameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

// --Commented out by Inspection START (2021-12-27 23:50):
//    public void execute(IGameEntity gameEntity) {
//        moveState.update(gameEntity);
//        executeNext();
//    }
// --Commented out by Inspection STOP (2021-12-27 23:50)

    public void setMoveState(_MoveState moveState) {
        this.moveState = moveState;
    }

    public void execute() {
        moveState.update(gameEntity);
        executeNext();
    }
}
