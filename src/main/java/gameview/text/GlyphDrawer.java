package gameview.text;

import javafx.scene.canvas.GraphicsContext;
import ui.CanvasMgr;

public class GlyphDrawer {

    private static GlyphDrawer instance;

    public GlyphDrawer() { }

    public static GlyphDrawer getInstance() {
        if (instance == null) {
            instance = new GlyphDrawer();
        }
        return instance;
    }

    public void draw(String string, double initialPosX, double initialPosY, double scale) {

        var graphicsContext = getGraphicsContext();
        var glyphMgr = GlyphMgr.getInstance();
        var fontsheet = GlyphMgr.getInstance().getFontsheet();

        var posX = initialPosX;
        var posY = initialPosY;


        for(int ch = 0; ch < string.length(); ch++) {
            var spriteImage = glyphMgr.get(string.charAt(ch));

            var width = spriteImage.w() * scale;
            var height = spriteImage.h() * scale;

            graphicsContext.drawImage(fontsheet,
                    spriteImage.x(), spriteImage.y(), spriteImage.w(), spriteImage.h(),
                    posX, posY, width, height);

            //advance posX to prevent letters from overlapping
            posX += width;
        }
    }

    private GraphicsContext getGraphicsContext() {
        var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_GLYPHS);
        return canvas.getGraphicsContext2D();
    }
}
