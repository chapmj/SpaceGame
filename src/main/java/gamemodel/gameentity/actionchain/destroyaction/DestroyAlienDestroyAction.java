package gamemodel.gameentity.actionchain.destroyaction;

import gamecontroller.collision.CollisionMgr;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

public class DestroyAlienDestroyAction extends _GameEntityActionChain {

    public DestroyAlienDestroyAction() { }

    public void _doAction(IGameEntity gameEntity) {
        var alien = gameEntity;
        var alienComponent = GameEntityMgr.getInstance().getComponent(alien);
        GameEntityMgr.getInstance().remove(GameEntityMgr.RootNames.ALIEN_ROOT, alien);
        CollisionMgr.getInstance().remove(alienComponent);
        AlienGridMarchMgr.getInstance().remove(alien);
    }
}
