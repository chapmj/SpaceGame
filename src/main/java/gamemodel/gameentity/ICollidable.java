package gamemodel.gameentity;

import gamemodel.gameentity.actionchain._GameEntityActionChain;

public interface ICollidable {
    void collide(IGameEntity gameEntity);
    void setHitAction(_GameEntityActionChain command);
    void addHitAction(_GameEntityActionChain action);
    _GameEntityActionChain getHitAction();
}
