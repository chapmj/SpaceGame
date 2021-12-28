package gamemodel.gameentity.actionchain.updateaction.ufostate;

import java.util.Objects;

public class UFODeployMgrRunningLeftState extends _UFODeployMgrRunningState {

    @Override
    public _UFODeployMgrState execute() {

        var isOffLeft = exceedsLeftBound();

        if (isOffLeft) {
            return nextState(context);
        }
        return RUNNING;
    }

    private boolean exceedsLeftBound() {
        Objects.requireNonNull(context);
        var gameEntity = context;
        var gameEntityX = gameEntity.getRect().x();
        var gameEntityW = gameEntity.getRect().w();

        var ufoOffLeftScreenAt = -gameEntityW;

        return (gameEntityX < ufoOffLeftScreenAt);

    }
}
