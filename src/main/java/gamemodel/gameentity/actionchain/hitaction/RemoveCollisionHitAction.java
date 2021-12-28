package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.collision.CollisionMgr;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

public class RemoveCollisionHitAction extends _GameEntityActionChain {

    private final IGameEntity gameEntity;

    public RemoveCollisionHitAction(IGameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var bombComponent = GameEntityMgr.getInstance().getComponent(gameEntity);
        CollisionMgr.getInstance().remove(bombComponent);
    }

}
