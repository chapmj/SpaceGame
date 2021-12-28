package gamemodel.gameentity.actionchain.destroyaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombDeployMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombUpdateAction;

public class BombActionDestroyAction extends _GameEntityActionChain {

    private final BombUpdateAction action;

    public BombActionDestroyAction(BombUpdateAction action) {
        this.action = action;

    }

    public void _doAction(IGameEntity gameEntity) {
        BombDeployMgr.getInstance().unsubscribe(action);
    }
}
