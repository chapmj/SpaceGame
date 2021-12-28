package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.HeatMgr;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class DecreaseHeatHitAction extends _GameEntityActionChain {

    public DecreaseHeatHitAction() {
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        HeatMgr.getInstance().decreaseHeat();
    }

}
