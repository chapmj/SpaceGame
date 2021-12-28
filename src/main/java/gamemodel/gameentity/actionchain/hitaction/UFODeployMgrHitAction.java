package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.ufostate.UFODeployMgr;

public class UFODeployMgrHitAction extends _GameEntityActionChain {

    public UFODeployMgrHitAction() { }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        UFODeployMgr.getInstance().reset();
    }


}
