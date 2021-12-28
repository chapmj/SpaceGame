package gamemodel.gameentity.actionchain.updateaction.ufostate;

import gamecontroller.RandomMgr;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;
import gamemodel.gameentity.factory.FactoryUFO;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import ui.CanvasMgr;

public class UFODeployMgrSpawningState extends _UFODeployMgrState {

    @Override
    public _UFODeployMgrState execute() {

        var seed = RandomMgr.getInstance().nextInt(2);

        var yPos = 80;

        if(seed % 2 == 0) {
            //left spawn
            FactoryUFO.getInstance().setMoveDirection(_MoveState.MOVE_EAST);
            var ufo = FactoryUFO.getInstance().create();

            var ufoWidth = ufo.getRect().w();

            var ufoComponent = new GameEntityLeaf(ufo);

            var offsetX = -10 - ufoWidth;
            ufoComponent.translatePosition(-ufoWidth + offsetX,yPos);

            GameEntityMgr.getInstance().add(GameEntityMgr.RootNames.UFO_ROOT, ufoComponent);
            setRunningState((_UFODeployMgrRunningState) RUNNING_RIGHT);
            return nextState(ufo);
        }
        else {
            //right spawn
            FactoryUFO.getInstance().setMoveDirection(_MoveState.MOVE_WEST);
            var ufo = FactoryUFO.getInstance().create();

            var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
            var canvasWidth = canvas.getWidth();

            var ufoWidth = ufo.getRect().w();

            var ufoComponent = new GameEntityLeaf(ufo);

            ufoComponent.translatePosition(canvasWidth + ufoWidth, yPos);

            GameEntityMgr.getInstance().add(GameEntityMgr.RootNames.UFO_ROOT, ufoComponent);
            setRunningState((_UFODeployMgrRunningState) RUNNING_LEFT);
            return nextState(ufo);

        }
    }
}
