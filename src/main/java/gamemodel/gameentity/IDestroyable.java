package gamemodel.gameentity;

import gamemodel.gameentity.actionchain._GameEntityActionChain;

public interface IDestroyable {
    void destroy();
    void setDestroyAction(_GameEntityActionChain command);
    _GameEntityActionChain getDestroyAction();
}
