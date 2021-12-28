package gamemodel.gameentity.factory;

import gamemodel.gameentity.gameentitycomponent.GameEntityComposite;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;
import gamemodel.gamerect.WRect;

public class FactoryShield {

    private final FactoryShieldCell cellFactory;

    public FactoryShield(FactoryShieldCell shieldCellFactory) {
        this.cellFactory = shieldCellFactory;
    }

    public _GameEntityComponent create() {
        var numRows = 4;//4
        var numCols = 7;

        var shieldW = 88;
        var shieldH = 64;

        var shieldRect = new WRect(0, 0, shieldW, shieldH);

        var shieldRoot = new GameEntityComposite(GameEntityMgr.RootNames.SHIELD_ROOT);

        //Create cells
        var cellWidth = shieldRect.w() / numCols;
        var cellHeight = shieldRect.h() / numRows;

        for(int colIdx = 0; colIdx < numCols; colIdx++) {
            var shieldCol = new GameEntityComposite(GameEntityMgr.RootNames.SHIELD_ROOT);
            for(int rowIdx = 0; rowIdx < numRows; rowIdx++) {
                var cell = cellFactory.create();

                //set cell position inside shield
                var cellX = shieldRect.x() + (cellWidth * colIdx);
                var cellY = shieldRect.y() + (cellHeight * rowIdx);

                cell.getRect().setWidth(cellWidth);
                cell.getRect().setHeight(cellHeight);
                cell.getRect().translate(cellX, cellY);

                shieldCol.add(new GameEntityLeaf(cell));

            }
            shieldRoot.add(shieldCol);
        }

        shieldRoot.init();

        return shieldRoot;
    }

}
