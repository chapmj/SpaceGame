package gamemodel.gameentity.actionchain.destroyaction;

import gamecontroller.collision.CollisionMgr;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

public class UFODestroyAction extends _GameEntityActionChain {

    public UFODestroyAction() { }

    public void _doAction(IGameEntity gameEntity) {
        var ufoEntity = gameEntity;
        var ufoComponent= GameEntityMgr.getInstance().getComponent(ufoEntity);
        GameEntityMgr.getInstance().remove(GameEntityMgr.RootNames.UFO_ROOT, ufoEntity);
        CollisionMgr.getInstance().remove(ufoComponent);
    }
}
