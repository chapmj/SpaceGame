package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.TimedCommandMgr;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.command.PlayTextCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class GameSystemGameOverState extends _GameSystemState {
    @Override
    public void _update() {

        var gameStats = GameStats.getInstance();
        var score = gameStats.getScore();
        var hiscore = gameStats.getHiscore();
        var lives = gameStats.getLives();

        var livesDisplayed = Math.max(lives, 0);

        TextMgr.getInstance().add(TextMgr.GameText.SCORE, String.format("SCORE %04d       HI-SCORE %04d", score, hiscore), new TextPosition(40, 10));
        TextMgr.getInstance().add(TextMgr.GameText.LIVES, String.format("LIVES %d     <-A D-> SPACE FIRE", livesDisplayed), new TextPosition(40, 690));

        var gameOverAnimation = new PlayTextCommand(TextMgr.GameText.GAME_OVER, "GAME OVER", (char)15, 300, 250);
        var gameOverTimedCommand = new TimedCommand(gameOverAnimation, GameTime.getInstance().getTimeWithDelay((char)40));
        TimedCommandMgr.getInstance().add(gameOverTimedCommand);

    }


    @Override
    public void handle() {

        var gameStateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.CLEANUP_STATE);
        var changeStateTimedCommand = new TimedCommand(gameStateChangeCommand, GameTime.getInstance().getTimeWithDelay((char)300));
        TimedCommandMgr.getInstance().add(changeStateTimedCommand);

        var stopCommand = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stopCommand.execute(); //STOP -> 300 -> CLEANUP

    }

    public String toString() {
        return "GAMEOVER STATE";
    }
}
