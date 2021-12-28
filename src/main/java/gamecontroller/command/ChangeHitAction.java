package gamecontroller.command;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class ChangeHitAction extends _GameEntityCommand {

    private final IGameEntity gameEntity;
    private final _GameEntityActionChain nextHitAction;

    public ChangeHitAction(IGameEntity gameEntity, _GameEntityActionChain nextHitAction) { 
        this.gameEntity = gameEntity;
        this.nextHitAction = nextHitAction;
    }

    public void execute() {
        gameEntity.setHitAction(nextHitAction);
    }
}
