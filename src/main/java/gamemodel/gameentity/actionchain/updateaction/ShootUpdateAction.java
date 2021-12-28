package gamemodel.gameentity.actionchain.updateaction;

import gamecontroller.input.KeySubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.shootstate._ShipShootState;


public class ShootUpdateAction extends _GameEntityActionChain implements KeySubscriber {
    boolean triggered;
    int coolAt = 20;
    int cooldown = 0;
    _ShipShootState shootState = _ShipShootState.STOPPED;

    public ShootUpdateAction() { }

    @Override
    public void _doAction(IGameEntity gameEntity) {
        if(triggered && cooldown >= coolAt) {
            shootState = _ShipShootState.FIRING;
            cooldown = 0;
        }

        if(cooldown < coolAt) {
            cooldown += 1;
        }

        shootState.update(gameEntity);

        if(shootState.equals(_ShipShootState.FIRING)) {
            shootState = _ShipShootState.STOPPED;
        }
    }

    @Override
    public void notifyPress(char key) {
        //var key = KeyboardController.getInstance().getPressed();
        if (key == ' ') {
            triggered = true;
        }
    }

    @Override
    public void notifyRelease(char key) {
        //var key = KeyboardController.getInstance().getReleased();
        if (key == ' ') {
            triggered = false;
        }
    }

}
