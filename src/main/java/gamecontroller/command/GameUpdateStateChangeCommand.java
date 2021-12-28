package gamecontroller.command;

import gamecontroller.gamesystem.GameSystemMgr;
import gamecontroller.gamesystem._GameSystemState;
import gamecontroller.gametime.GTLogger;

public class GameUpdateStateChangeCommand implements _ICommand {

    private _GameSystemState nextGameSystemState;

    public GameUpdateStateChangeCommand(_GameSystemState nextGameSystemState) {

        this.nextGameSystemState = nextGameSystemState;
    }

    @Override
    public void execute() {
        var gameSystem = GameSystemMgr.getInstance().get(GameSystemMgr.GameSystemName.MAIN_GAME);
        gameSystem.setState(nextGameSystemState);
        GTLogger.log(nextGameSystemState.toString());

    }
}
