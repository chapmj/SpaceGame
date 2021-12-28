package gamecontroller.command;

import gamecontroller.GameStats;
import gamecontroller.TimedCommandMgr;
import gamecontroller.gamesystem._GameSystemState;
import gamecontroller.gametime.GameTime;
import gameview.text.TextMgr;

public class GameOverCommand extends _GameEntityCommand {

    public void execute() {
        GameStats.getInstance().setLives(0);
        var gameOverAnimation = new PlayTextCommand(TextMgr.GameText.GAME_OVER, "GAME OVER", (char)15, 300, 250);
        var gameOverTimedCommand = new TimedCommand(gameOverAnimation, GameTime.getInstance().getTimeWithDelay((char)40));
        TimedCommandMgr.getInstance().add(gameOverTimedCommand);

        var gameStateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.CLEANUP_STATE);
        var changeStateTimedCommand = new TimedCommand(gameStateChangeCommand, GameTime.getInstance().getTimeWithDelay((char)300));
        TimedCommandMgr.getInstance().add(changeStateTimedCommand);

        var stopCommand = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stopCommand.execute();

    }

}
