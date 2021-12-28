package gamemodel.gameentity.actionchain.updateaction.bombshootstate;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class BombUpdateAction extends _GameEntityActionChain {

    _BombShootState shootState = _BombShootState.STOPPED;

    public BombUpdateAction() { }

    @Override
    public void _doAction(IGameEntity gameEntity) {

        shootState.update(gameEntity);

        if(shootState.equals(_BombShootState.FIRING)) {
            shootState = shootState.nextState();
        }
    }

    public void fire() {
        shootState = _BombShootState.FIRING;
    }
}
