package gamecontroller.collision;

import gamecontroller.gametime.GTLogger;
import gamecontroller.input.KeyMap;
import gamecontroller.input.KeySubscriber;
import gamecontroller.input.KeyboardController;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ui.CanvasMgr;

public class CollisionDrawer implements KeySubscriber
{
    private static CollisionDrawer instance;
    private static boolean DEBUG_ON = false;
    private boolean debugToggleLock;
    private GraphicsContext graphicsContext;
    private double drawerX;
    private double drawerY;
    private double drawerW;
    private double drawerH;

    private CollisionDrawer() { }

    public static CollisionDrawer getInstance() {
        if (instance == null) {
            instance = new CollisionDrawer();
        }
        return instance;
    }

    public static void init() {
        instance = getInstance();
        var canvasDebug = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_DEBUG);
        instance.graphicsContext = canvasDebug.getGraphicsContext2D();
        instance.graphicsContext.setLineWidth(1);
        instance.graphicsContext.setStroke(Color.RED);
        KeyboardController.getInstance().createSubscription(instance, KeyMap.F10);

        instance.drawerX = canvasDebug.getLayoutX();
        instance.drawerY = canvasDebug.getLayoutY();
        instance.drawerW = canvasDebug.getWidth();
        instance.drawerH = canvasDebug.getHeight();
    }

    public void draw() {
        if(DEBUG_ON) {
            drawClear();
            var roots = GameEntityMgr.getInstance().iterator();

            while(roots.hasNext()) {
                var gameEntityComponent = roots.next();
                var rect = gameEntityComponent.getRect();
                this.graphicsContext.strokeRect(rect.x(), rect.y(), rect.w(), rect.h());
            }
        }
    }

    public void drawClear() {
        var canvasDebug = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_DEBUG);
        //this.graphicsContext.clearRect(canvasDebug.getLayoutX(), canvasDebug.getLayoutY(), canvasDebug.getWidth(), canvasDebug.getWidth());
        this.graphicsContext.clearRect(drawerX, drawerY, drawerW, drawerH);
    }

    public static void toggleDebug() {

        //var canvas = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_DEBUG);
        //var graphicsContext = canvas.getGraphicsContext2D();
        //graphicsContext.clearRect(canvas.getLayoutX(), canvas.getLayoutY(), canvas.getWidth(), canvas.getWidth());
        getInstance().drawClear();

        DEBUG_ON = !DEBUG_ON;

        GTLogger.log("DEBUG TOGGLED");
    }

    @Override
    public void notifyPress(char key) {
        if(!debugToggleLock) {
            toggleDebug();
            debugToggleLock = true;
        }
    }

    @Override
    public void notifyRelease(char key) {
        debugToggleLock = false;
    }

}
