package gamemodel.gameentity.actionchain.updateaction.keystate;

import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

public class KeyStateMiddle extends _KeyState {
    @Override
    public _MoveState getKeyState(char key) {
        return switch(key) {
            case 'D' -> _MoveState.MOVE_EAST;
            case 'A' -> _MoveState.MOVE_WEST;
            default -> _MoveState.MOVE_NULL;
        };
    }
}
