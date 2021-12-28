package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class GameSystemShipHitState extends _GameSystemState {
    @Override
    public void _update() {
        GameStats.getInstance().update();

        var gameStats = GameStats.getInstance();
        var score = gameStats.getScore();
        var hiscore = gameStats.getHiscore();
        var lives = gameStats.getLives();

        var livesDisplayed = Math.max(lives, 0);

        TextMgr.getInstance().add(TextMgr.GameText.SCORE, String.format("SCORE %04d       HI-SCORE %04d", score, hiscore), new TextPosition(40, 10));
        TextMgr.getInstance().add(TextMgr.GameText.LIVES, String.format("LIVES %d     <-A D-> SPACE FIRE", livesDisplayed), new TextPosition(40, 690));

    }

    @Override
    protected void handle() {
        if(GameStats.getInstance().getLives() < 0) {
            //GameOver State
            var stateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.GAME_OVER_STATE);
            stateChangeCommand.execute();
        }

        else {
            //stay in running state
            var stateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.RUNNING_STATE);
            stateChangeCommand.execute();
        }
    }

    public String toString() {

        return "SHIP HIT STATE";
    }

}
