package gamemodel.gameentity.actionchain;

import gamemodel.gameentity.IGameEntity;

import java.util.Objects;

public abstract class _GameEntityActionChain {

    protected _GameEntityActionChain nextAction = null;

    public abstract void _doAction (IGameEntity gameEntity);

    public void doAction(IGameEntity gameEntity) {
        _doAction(gameEntity);
        if(nextAction != null) {
            nextAction.doAction(gameEntity);
        }

    }

    public void addAction(_GameEntityActionChain action) {
        Objects.requireNonNull(action);

        if(nextAction == null) {
            nextAction = action;
        }
        else {
            nextAction.addAction(action);
        }
    }

}
