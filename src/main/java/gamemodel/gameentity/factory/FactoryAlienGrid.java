package gamemodel.gameentity.factory;

import gamecontroller.GameStats;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.BombActionDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.AlienInWallHitAction;
import gamemodel.gameentity.actionchain.hitaction.ChoiceAction;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombDeployMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombUpdateAction;
import gamemodel.gameentity.gameentitycomponent.GameEntityComposite;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;

import java.util.ArrayList;
import java.util.Arrays;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.ALIEN_ROOT;
import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.WALL_ROOT;

public class FactoryAlienGrid {

    public _GameEntityComponent createGrid(int numCols, int paddingX, int paddingY, FactoryAlien... alienFactories) {

        var alienRoot = new GameEntityComposite(ALIEN_ROOT);
        createCols(numCols, alienRoot);
        _GameEntityComponent[] components = new _GameEntityComponent[numCols]; //gc
        var cols = alienRoot.getChildren().toArray(components);

        for(int i = 0; i < alienFactories.length; i++) {
            var alienRow = createRow(alienFactories[i], cols);
            ArrayList<IGameEntity> alienList = new ArrayList<>(Arrays.asList(alienRow));

            if(i % 2 == 1) {//set odd rows to different style
                for (IGameEntity alien: alienList) {
                    var sprite = alien.getSprite();
                    sprite.nextFrame();
                }
            }
            AlienGridMarchMgr.getInstance().add(alienList);
        }

        spreadColumns(alienRoot, paddingX);
        spreadRows(alienRoot, paddingY);


        var hitAction = new ChoiceAction(alienRoot);
        hitAction.add(WALL_ROOT, new AlienInWallHitAction());

        alienRoot.setLocalHitAction(hitAction);

        alienRoot.init();//calculate rects initially

        return alienRoot;
    }

    public void createCols(int numCols, GameEntityComposite root) {
        for(int i = 0; i < numCols; i++) {
            var col = new GameEntityComposite(ALIEN_ROOT);
            var bombUpdateAction = new BombUpdateAction();
            col.setLocalUpdateAction(bombUpdateAction);
            BombDeployMgr.getInstance().subscribe(bombUpdateAction);
            col.setLocalDestroyAction(new BombActionDestroyAction(bombUpdateAction));
            root.add(col);
        }
    }

    private IGameEntity[] createRow(FactoryAlien alienFactory, _GameEntityComponent... columns) {

            if(columns != null) {
                var alienRow = new IGameEntity[columns.length];

                for (int j = 0; j < columns.length; j++) {
                    var alien = alienFactory.create();
                    alien.setSpeed(15);

                    alienRow[j] = alien;

                    var alienComponent = new GameEntityLeaf(alien);
                    alienComponent.setName(ALIEN_ROOT);
                    columns[j].add(alienComponent);
                    GameStats.getInstance().increaseAliensCount();
                }
                return alienRow;
            }
            return null;
        }

    private void spreadColumns(_GameEntityComponent root, int paddingHorizontal) {

        var cols = root.getChildren();
        double xPosition = 0;

        for(int colIdx = 0; colIdx < cols.size(); colIdx++) {
            var col = cols.get(colIdx);
            var records = col.getChildren();
            double columnWidth = 0;

            //get width of entire column
            for(int i = 0; i < records.size(); i++) {
                var memberWidth = records.get(i).getRect().w();
                if (memberWidth > columnWidth) {
                    columnWidth = memberWidth;
                }
            }

            for(int j = 0; j < records.size(); j++) {
                var recordRect = records.get(j).getRect();
                var centering = (columnWidth - recordRect.w()) * 0.5;
                recordRect.translate(xPosition + centering, 0);
            }

            xPosition += columnWidth + paddingHorizontal;

        }

    }

    private void spreadRows(_GameEntityComponent root, int paddingVertical) {

        var cols = root.getChildren();
        for(int colIdx = 0; colIdx < cols.size(); colIdx++) {

            var rows = cols.get(colIdx).getChildren();
            double height = 0;

            for(int i = 0; i < rows.size(); i++) {
                var memberHeight = rows.get(i).getRect().h();
                rows.get(i).getRect().translate(0, height);
                height += memberHeight + paddingVertical;
            }
        }

    }
}
