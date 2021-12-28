package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

public class RemoveGameEntityHitAction extends _GameEntityActionChain {

    private final IGameEntity gameEntity;
    private final GameEntityMgr.RootNames rootName;

    public RemoveGameEntityHitAction(GameEntityMgr.RootNames rootName, IGameEntity gameEntity) {
        this.rootName = rootName;
        this.gameEntity = gameEntity;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        GameEntityMgr.getInstance().remove(rootName, gameEntity);
    }

}
