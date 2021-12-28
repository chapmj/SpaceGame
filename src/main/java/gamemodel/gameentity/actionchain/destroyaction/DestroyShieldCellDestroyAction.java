package gamemodel.gameentity.actionchain.destroyaction;

import gamecontroller.collision.CollisionMgr;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

public class DestroyShieldCellDestroyAction extends _GameEntityActionChain {

    public DestroyShieldCellDestroyAction() { }

    public void _doAction(IGameEntity gameEntity) {
        var shieldCell = gameEntity;
        var shieldCellComponent = GameEntityMgr.getInstance().getComponent(shieldCell);
        GameEntityMgr.getInstance().remove(GameEntityMgr.RootNames.SHIELD_ROOT, shieldCell);
        CollisionMgr.getInstance().remove(shieldCellComponent);
    }
}
