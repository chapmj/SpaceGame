package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;

public class PauseMarchHitAction extends _GameEntityActionChain {

    private final int duration;

    public PauseMarchHitAction(int duration) {
        this.duration = duration;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        AlienGridMarchMgr.getInstance().pause((char) duration);
    }

}
