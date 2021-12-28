package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.actionchain.updateaction.KeyUpdateAction;

public class HandleKeyAction extends _GameEntityActionChain {

    private KeyUpdateAction keyAction;

    @Override
    public void _doAction(IGameEntity gameEntity) {
        if(keyAction != null) {
            keyAction.handle();
        }
    }

    public void setKeyActionReference(KeyUpdateAction keyAction) {
        this.keyAction = keyAction;
    }
}
