package gamecontroller.command;

import gamemodel.gameentity.factory.FactoryShip;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import ui.CanvasMgr;

public class CreateShipCommand extends _GameEntityCommand {

    @Override
    public void execute() {
            var shipFactory = FactoryShip.getInstance();

            var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
            var canvasHeight = canvas.getHeight();

            var ship = shipFactory.create();
            var newWidth = 120;

            var offset = 50;
            var y = canvasHeight - ship.getRect().h() - offset;

            ship.getRect().positionRect(newWidth, y);

            GameEntityMgr.getInstance().addLeaf(ship.getName(), ship);
    }

}
