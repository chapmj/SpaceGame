package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.TimedCommandMgr;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.command.PlayTextCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class GameSystemTitleMenuState extends _GameSystemState {

    @Override
    public void _update() { //need to do this without using enums

        TimedCommandMgr.getInstance().clear();
        TextMgr.getInstance().clear();
        GameStats.getInstance().reset();

        var gameStats = GameStats.getInstance();
        var score = gameStats.getScore();
        var hiscore = gameStats.getHiscore();
        TextMgr.getInstance().add(TextMgr.GameText.SCORE, String.format("SCORE %04d       HI-SCORE %04d", score, hiscore), new TextPosition(40, 10));

        var title = new PlayTextCommand(TextMgr.GameText.TITLE, "SPACE GAME", (char)15, 280, 250);
        var titleTimedCommand= new TimedCommand(title, GameTime.getInstance().getTimeWithDelay((char)30));
        TimedCommandMgr.getInstance().add(titleTimedCommand);

    }

    @Override
    protected void handle() {
        var transitionCommand = new GameUpdateStateChangeCommand(_GameSystemState.INITIALIZING_STATE);
        var transitionTimedCommand = new TimedCommand(transitionCommand, GameTime.getInstance().getTimeWithDelay((char)360));
        TimedCommandMgr.getInstance().add(transitionTimedCommand);

        var stopCommand = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stopCommand.execute();
    }


    @Override
    public String toString() {
        return "TITLE_MENU STATE";
    }
}
