package gameview.gameimage;

import javafx.scene.canvas.GraphicsContext;
import ui.CanvasMgr;
import ui.StageMgr;

public class SpriteDrawer {

    private static SpriteDrawer instance;

    public SpriteDrawer() { }

    public static SpriteDrawer getInstance() {
        if (instance == null) {
            instance = new SpriteDrawer();
        }
        return instance;
    }

    public void draw() {

    //keeps a big list of sprites to draw

        var sprites = SpriteMgr.getInstance().getAll();
        var spriteItr = sprites.iterator();
        var spritesheet = ImageMgr.getInstance().getSpritesheet();
        var graphicsContext = getGraphicsContext();

        while (spriteItr.hasNext()) {
            var gameSprite = spriteItr.next();
            var image = gameSprite.getImage();

            gameSprite.initialize();
            while (gameSprite.hasNext()) {
                gameSprite.updateNext();
                graphicsContext.drawImage(spritesheet, image.x(), image.y(), image.w(), image.h(),
                        gameSprite.getX(), gameSprite.getY(), gameSprite.getW(), gameSprite.getH());
            }
        }
    }

    private GraphicsContext getGraphicsContext() {
        var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);
        return canvas.getGraphicsContext2D();
    }
}
