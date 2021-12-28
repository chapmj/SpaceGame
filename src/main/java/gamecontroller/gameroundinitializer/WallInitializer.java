package gamecontroller.gameroundinitializer;

import gamecontroller.command.GameOverCommand;
import gamemodel.gameentity.actionchain.hitaction.ChoiceAction;
import gamemodel.gameentity.actionchain.hitaction.CommandExecutorAction;
import gamemodel.gameentity.factory.FactoryWall;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import ui.CanvasMgr;

public class WallInitializer extends _Initializer {
    @Override
    public void init() {

        var wallFactory = FactoryWall.getInstance();

        var enemiesCanvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var canvasWidth = enemiesCanvas.getWidth();
        var canvasHeight = enemiesCanvas.getHeight();

        var wallLeft = wallFactory.create(40, 55, 1, canvasHeight - 90 );
        GameEntityMgr.getInstance().addLeaf(GameEntityMgr.RootNames.WALL_ROOT, wallLeft);

        var wallTop = wallFactory.create(40, 55, canvasWidth - 80, 1);
        GameEntityMgr.getInstance().addLeaf(GameEntityMgr.RootNames.WALL_ROOT, wallTop);

        var wallRight = wallFactory.create(canvasWidth - 40, 55, 1, canvasHeight - 90);
        GameEntityMgr.getInstance().addLeaf(GameEntityMgr.RootNames.WALL_ROOT, wallRight);

        var wallBottom = wallFactory.create(40, canvasHeight - 40 -10, canvasWidth - 80, 1);
        var hitAction = new ChoiceAction(wallBottom);
        hitAction.add(GameEntityMgr.RootNames.ALIEN_ROOT, new CommandExecutorAction(new GameOverCommand()));

        wallBottom.addHitAction(hitAction);
        GameEntityMgr.getInstance().addLeaf(GameEntityMgr.RootNames.WALL_ROOT, wallBottom);

    }

}
