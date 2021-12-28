package gameview.gameimage;

import gamemodel.gamerect.IRect;
import ui.CanvasMgr;

public class RectDrawer {

    private static RectDrawer instance;

    public RectDrawer() { }

    public static RectDrawer getInstance() {
        if (instance == null) {
            instance = new RectDrawer();
        }
        return instance;
    }

    public void draw() {
        //keeps a big list of rects to draw
        var rects = RectMgr.getInstance().getAll();

        var canvasForEnemies = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var gcEnemies= canvasForEnemies.getGraphicsContext2D();

        for(IRect rect : rects) {
            gcEnemies.clearRect(rect.x(), rect.y(), rect.w(), rect.h());
        }

    }

}
