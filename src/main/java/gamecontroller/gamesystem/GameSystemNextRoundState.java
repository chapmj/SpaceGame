package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.TimedCommandMgr;
import gamecontroller.command.GameSystemCleanupCommand;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.command.PlayTextCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gameview.text.TextMgr;

public class GameSystemNextRoundState extends _GameSystemState {
    char titleMenuStartTime = 0;

    @Override
    public void _update() {

        if(GameStats.getInstance().getRound() == 2) {
            //GameWin State
            GameStats.getInstance().setLives(0);
            var youWinTxt = "EARTH IS SAVED";
            char youWinStartTime = 40;
            char youWinCharDelay = 15;
            char youWinLen = (char)(youWinTxt.length() * youWinCharDelay + youWinStartTime + 30);
            titleMenuStartTime = (char)(youWinLen + 120);

            var youWinAnimation = new PlayTextCommand(TextMgr.GameText.GAME_OVER, youWinTxt, youWinCharDelay, 240, 250);
            var youWinTimedCommand = new TimedCommand(youWinAnimation, GameTime.getInstance().getTimeWithDelay(youWinStartTime));
            TimedCommandMgr.getInstance().add(youWinTimedCommand);

            var cleanupCmd = new TimedCommand(new GameSystemCleanupCommand(), GameTime.getInstance().getTimeWithDelay((youWinLen)));
            TimedCommandMgr.getInstance().add(cleanupCmd);
        }
    }

    @Override
    protected void handle() {
        if(GameStats.getInstance().getRound() == 2) {
            var titleMenuCommand = new GameUpdateStateChangeCommand(_GameSystemState.TITLE_MENU_STATE);
            var changeStateTimedCommand = new TimedCommand(titleMenuCommand, GameTime.getInstance().getTimeWithDelay(titleMenuStartTime));
            TimedCommandMgr.getInstance().add(changeStateTimedCommand);

        }
        else {
                //NextRound State
                var newRoundCmd = new TimedCommand(new GameUpdateStateChangeCommand(INITIALIZING_STATE), GameTime.getInstance().getTimeWithDelay((char)200));
                TimedCommandMgr.getInstance().add(newRoundCmd);
                var cleanupCmd = new TimedCommand(new GameSystemCleanupCommand(), GameTime.getInstance().getTimeWithDelay((char)(200)));
                TimedCommandMgr.getInstance().add(cleanupCmd);
        }
        var stopCommand = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stopCommand.execute();

        GameStats.getInstance().increaseGameRound();
    }

    public String toString() {
        return "NEXT_ROUND STATE";
    }
}
