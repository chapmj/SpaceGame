package gamemodel.gameentity.actionchain.updateaction.ufostate;

import ui.CanvasMgr;

import java.util.Objects;

public class UFODeployMgrRunningRightState extends _UFODeployMgrRunningState {

    @Override
    public _UFODeployMgrState execute() {

        var isOffRight = exceedsRightBound();

        if (isOffRight) {
            return nextState(context);
        }
        return RUNNING;
    }

    private boolean exceedsRightBound() {
        Objects.requireNonNull(context);
        var gameEntity = context;
        var gameEntityX = gameEntity.getRect().x();
        var gameEntityW = gameEntity.getRect().w();
        var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var canvasWidth = canvas.getWidth();

        var ufoOffRightScreenAt = canvasWidth + gameEntityW * 0.5;

        return (gameEntityX > ufoOffRightScreenAt);

    }
}
