package gamecontroller.gamesystem;

import gamecontroller.TimedCommandMgr;
import gamecontroller.collision.CollisionDrawer;
import gamecontroller.gametime.GTLogger;
import gamecontroller.gametime.GameTime;
import gamecontroller.input.KeySubscriber;
import gamecontroller.input.KeyboardController;
import gameview.gameimage.ImageDrawer;
import gameview.gameimage.RectDrawer;
import gameview.gameimage.SpriteDrawer;
import gameview.text.TextMgr;
import javafx.animation.AnimationTimer;
import ui.StageMgr;

public class GameSystem extends AnimationTimer implements KeySubscriber
{
    private _GameSystemState updateState;
    private boolean debugToggleLock;

    public GameSystem() { }

    @Override
    public void start() {
        setState(_GameSystemState.TITLE_MENU_STATE);
        GTLogger.log(_GameSystemState.TITLE_MENU_STATE.toString());
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void handle(long now) {
        var stage = StageMgr.getInstance().get(StageMgr.Stages.STAGE_GAME);
        if(!stage.isIconified()) {
            update();
            draw();
        }
    }

    public void update() {
        //All scenes
        GameTime.getInstance().update();
        KeyboardController.getInstance().update();
        TimedCommandMgr.getInstance().update();

        updateState.update();
    }

    public void draw() {
        ImageDrawer.getInstance().drawClear();
        SpriteDrawer.getInstance().draw();
        RectDrawer.getInstance().draw();
        CollisionDrawer.getInstance().draw(); //debug only
        TextMgr.getInstance().draw();
    }

    @Override
    public void notifyPress(char key) {
        if(!debugToggleLock) {
            //this.updateState = updateState.handle();
            debugToggleLock = true;
        }
    }

    @Override
    public void notifyRelease(char key) {
        debugToggleLock = false;
    }

    public void setState(_GameSystemState updateState) {
        this.updateState = updateState;
    }
}
