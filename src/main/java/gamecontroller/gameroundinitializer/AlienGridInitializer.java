package gamecontroller.gameroundinitializer;

import gamecontroller.GameStats;
import gamemodel.gameentity.factory.FactoryAlien;
import gamemodel.gameentity.factory.FactoryAlienGrid;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import ui.CanvasMgr;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.ALIEN_ROOT;


public class AlienGridInitializer extends _Initializer {


    public void init() {
        var NUM_COLS = 11;
        var ALIEN_GRID_START_POS = 150;
        var ALIEN_GRID_ROUND_CREEP = 50;

        FactoryAlienGrid factory = new FactoryAlienGrid();
        var crabFactory0 = FactoryAlien.getFactory(FactoryAlien.AlienType.CRAB);
        var crabFactory1 = FactoryAlien.getFactory(FactoryAlien.AlienType.CRAB);
        var octopusFactory0 = FactoryAlien.getFactory(FactoryAlien.AlienType.OCTOPUS);
        var octopusFactory1  = FactoryAlien.getFactory(FactoryAlien.AlienType.OCTOPUS);
        var squidFactory0 = FactoryAlien.getFactory(FactoryAlien.AlienType.SQUID);

        var alienGrid = factory.createGrid(NUM_COLS, 15, 10,
                squidFactory0, crabFactory0, crabFactory1, octopusFactory0, octopusFactory1);

        var enemiesCanvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var canvasWidth = enemiesCanvas.getWidth();

        var gridWidth = alienGrid.getRect().w();
        var newWidth = (canvasWidth - gridWidth) * 0.5;


        var gameRound = GameStats.getInstance().getRound();
        var roundModifier = ALIEN_GRID_ROUND_CREEP * gameRound;

        alienGrid.translatePosition((int)newWidth, ALIEN_GRID_START_POS + roundModifier);

        GameEntityMgr.getInstance().add(ALIEN_ROOT, alienGrid);
        var alienRoot = GameEntityMgr.getInstance().get(ALIEN_ROOT);
        alienGrid.setParent(alienRoot);

    }
}
