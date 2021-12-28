package gameview.gameimage;

import ui.CanvasMgr;

public class ImageDrawer {


    private static ImageDrawer instance;

    private ImageDrawer() { }

    public static ImageDrawer getInstance() {
        if (instance == null) {
            instance = new ImageDrawer();
        }
        return instance;
    }

    public void drawClear() {
        var canvasForEnemies = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        var gcEnemies= canvasForEnemies.getGraphicsContext2D();
        gcEnemies.clearRect(canvasForEnemies.getLayoutX(), canvasForEnemies.getLayoutY(),
                canvasForEnemies.getWidth(), canvasForEnemies.getWidth());

        var canvasForGlyphs = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_GLYPHS);
        var gcGlyphs = canvasForGlyphs.getGraphicsContext2D();
        gcGlyphs.clearRect(canvasForGlyphs.getLayoutX(), canvasForGlyphs.getLayoutY(),
                canvasForGlyphs.getWidth(), canvasForGlyphs.getHeight());
    }
}
