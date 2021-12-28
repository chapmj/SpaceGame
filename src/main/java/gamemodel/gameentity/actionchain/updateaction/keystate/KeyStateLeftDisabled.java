package gamemodel.gameentity.actionchain.updateaction.keystate;

import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

public class KeyStateLeftDisabled extends _KeyState {
    @Override
    public _MoveState getKeyState(char key) {
        return switch(key) {
            case 'D' -> _MoveState.MOVE_EAST;
            case 'A' -> _MoveState.STOPPED;
            default -> _MoveState.MOVE_NULL;
        };
    }
}
