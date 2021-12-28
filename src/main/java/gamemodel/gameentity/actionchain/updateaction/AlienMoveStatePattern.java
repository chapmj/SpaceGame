package gamemodel.gameentity.actionchain.updateaction;

import gamemodel.gameentity.actionchain.updateaction.movestate.MoveSouthFixedSpeed;
import gamemodel.gameentity.actionchain.updateaction.movestate.MoveStateHolder;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

import java.util.HashMap;
import java.util.Objects;

public class AlienMoveStatePattern {

    private HashMap<_MoveState, _MoveState> moveTable;
    private static AlienMoveStatePattern instance;

    private AlienMoveStatePattern() {

        moveTable = new HashMap<>();
        moveTable.put(_MoveState.MOVE_EAST, _MoveState.MOVE_SOUTH_FIXED);
        moveTable.put(_MoveState.MOVE_WEST, _MoveState.MOVE_SOUTH_FIXED);
        moveTable.put(_MoveState.MOVE_SOUTH_FIXED, _MoveState.MOVE_SOUTH_FIXED);

        ((MoveSouthFixedSpeed)_MoveState.MOVE_SOUTH_FIXED).setSpeed(20); // REFACTOR out
    }

    public static AlienMoveStatePattern GetInstance() {
        if(instance == null) {
            instance = new AlienMoveStatePattern();
        }
        return instance;
    }

    public static void NextState(MoveStateHolder moveStateHolder) {

        var currMoveState = moveStateHolder.getMoveState();
        var instance = GetInstance();

        if(currMoveState == _MoveState.MOVE_WEST) {
            instance.moveTable.put(_MoveState.MOVE_SOUTH_FIXED, _MoveState.MOVE_EAST);
        }

        if(currMoveState == _MoveState.MOVE_EAST) {
            instance.moveTable.put(_MoveState.MOVE_SOUTH_FIXED, _MoveState.MOVE_WEST);
        }


        var nextMoveState = Objects.requireNonNullElse(instance.moveTable.get(currMoveState), _MoveState.STOPPED);

        moveStateHolder.setMoveState(nextMoveState);
    }
}
