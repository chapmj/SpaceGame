package gamemodel.gameentity;

import gamemodel.gameentity.actionchain._GameEntityActionChain;

public interface IUpdateable {
    void update();
    void setUpdateAction(_GameEntityActionChain command);
}
