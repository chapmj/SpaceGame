package gamecontroller.gameroundinitializer;

import gamemodel.gameentity.factory.FactoryShieldGrid;
import gamemodel.gameentity.gameentitycomponent.GameEntityLockable;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;
import ui.CanvasMgr;

public class ShieldInitializer extends _Initializer {
    @Override
    public void init() {
        var numShields = 4;
        var shieldPadding = 78;

        var enemiesCanvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var canvasWidth = enemiesCanvas.getWidth();
        var canvasHeight = enemiesCanvas.getHeight();

        var factoryShieldGrid = new FactoryShieldGrid(numShields, shieldPadding);
        var shieldGrid = factoryShieldGrid.createGrid();
        var gridWidth = shieldGrid.getRect().w();
        var newWidth = (canvasWidth - gridWidth) * 0.5;

        shieldGrid.translatePosition(newWidth, canvasHeight - 200);

        var shields = shieldGrid.getChildren();
        for (_GameEntityComponent shield : shields) {
            var lockableShield = (GameEntityLockable) shield;
            lockableShield.init();
            lockableShield.lockPosition();
        }

        GameEntityMgr.getInstance().add(GameEntityMgr.RootNames.SHIELD_ROOT, shieldGrid);
    }
}
