package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.collision.CollisionDrawer;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.gameroundinitializer.*;
import gamecontroller.gametime.GameTime;
import gamecontroller.input.KeyMap;
import gamecontroller.input.KeyboardController;
import gameview.sound.SoundInitializer;
import gameview.text.GlyphMgr;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class GameSystemInitializingState extends _GameSystemState {
    @Override
    public void _update() {
        GameTime.init();
        KeyboardController.init();
        CollisionDrawer.init();
        GlyphMgr.getInstance().init();
        BaseInitializer gameInitializer = new BaseInitializer(
                new AlienGridInitializer(),
                new ShipInitializer(),
                new WallInitializer(),
                new ShieldInitializer(),
                new CollisionInitializer(),
                new SoundInitializer()
        );

        gameInitializer.init();


        var gamesystem = GameSystemMgr.getInstance().get(GameSystemMgr.GameSystemName.MAIN_GAME);
        KeyboardController.getInstance().createSubscription(gamesystem, KeyMap.F11);
        TextMgr.getInstance().clear();
        var score = GameStats.getInstance().getScore();
        var hiscore = GameStats.getInstance().getHiscore();
        var livesDisplayed = GameStats.getInstance().getLives();
        TextMgr.getInstance().add(TextMgr.GameText.SCORE, String.format("SCORE %04d       HI-SCORE %04d", score, hiscore), new TextPosition(40, 10));
        TextMgr.getInstance().add(TextMgr.GameText.LIVES, String.format("LIVES %d     <-A D-> SPACE FIRE", livesDisplayed), new TextPosition(40, 690));


    }

    @Override
    protected void handle() {
        var nextGameState = new GameUpdateStateChangeCommand(_GameSystemState.NEW_ROUND_ANIMATION_STATE);
        nextGameState.execute();
    }

    public String toString() {
        return "INITIALIZING STATE";
    }
}
