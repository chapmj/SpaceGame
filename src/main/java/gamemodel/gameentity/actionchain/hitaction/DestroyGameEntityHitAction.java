package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class DestroyGameEntityHitAction extends _GameEntityActionChain {
    final IGameEntity gameEntity;

    public DestroyGameEntityHitAction(IGameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        gameEntity.destroy();
    }

}
