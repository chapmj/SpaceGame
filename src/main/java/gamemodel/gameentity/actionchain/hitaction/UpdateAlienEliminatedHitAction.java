package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.GameStats;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class UpdateAlienEliminatedHitAction extends _GameEntityActionChain {

    public UpdateAlienEliminatedHitAction() {
        super();
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        GameStats.getInstance().decreaseAliensCount();
    }
}
