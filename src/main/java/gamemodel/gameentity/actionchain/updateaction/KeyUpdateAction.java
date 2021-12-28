package gamemodel.gameentity.actionchain.updateaction;

import gamecontroller.gametime.GameTime;
import gamecontroller.input.KeySubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.keystate._KeyState;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

public class KeyUpdateAction extends _GameEntityActionChain implements KeySubscriber {

    private _MoveState moveState = _MoveState.STOPPED;
    private _KeyState keyState = _KeyState.ALL_ACTIVE;
    private int handleTime;

    @Override
    public void _doAction(IGameEntity gameEntity) {
        var now = GameTime.getInstance().now();

        if(handleTime < now) {
            keyState = _KeyState.ALL_ACTIVE;
        }

        moveState.update(gameEntity);
    }

    private _MoveState getKeyState(char key) {
        return keyState.getKeyState(key);
    }

// --Commented out by Inspection START (2021-12-27 23:50):
//    public _MoveState getMoveState() {
//        return this.moveState;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:50)

    @Override
    public void notifyPress(char key) {
        //var key = KeyboardController.getInstance().getPressed();
        var tempKeyState = getKeyState(key);

        if(tempKeyState != null && tempKeyState != _MoveState.MOVE_NULL) {
            moveState = tempKeyState;
        }
    }

    @Override
    public void notifyRelease(char key) {
        //var key = KeyboardController.getInstance().getReleased();
        if (getKeyState(key).equals(moveState)) {
            moveState = _MoveState.STOPPED;
        }

    }

    public void handle() {
        this.handleTime = GameTime.getInstance().getTimeNext();
        if(keyState == _KeyState.ALL_ACTIVE) {
            if(moveState == _MoveState.MOVE_EAST) {
                keyState = _KeyState.RIGHT_DISABLED;
                moveState = _MoveState.STOPPED;
            }
            else if(moveState == _MoveState.MOVE_WEST) {
                keyState = _KeyState.LEFT_DISABLED;
                moveState = _MoveState.STOPPED;
            }

        }
    }
}
