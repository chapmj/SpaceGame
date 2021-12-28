package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;

public class AlienInWallHitAction extends _GameEntityActionChain {

    public AlienInWallHitAction() {
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {

        AlienGridMarchMgr.getInstance().setInsideWall();

    }

}
