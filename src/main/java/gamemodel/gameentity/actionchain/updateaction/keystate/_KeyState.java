package gamemodel.gameentity.actionchain.updateaction.keystate;

import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

public abstract class _KeyState {
    public static _KeyState ALL_ACTIVE = new KeyStateMiddle();
    public static _KeyState LEFT_DISABLED = new KeyStateLeftDisabled();
    public static _KeyState RIGHT_DISABLED = new KeyStateRightDisabled();

    public abstract _MoveState getKeyState(char key);
}
