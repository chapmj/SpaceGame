package gamecontroller.command;

import gamecontroller.GameStats;

public class MissileAvailableCommand extends _GameEntityCommand {

    public MissileAvailableCommand() { }

    public void execute() {
        GameStats.getInstance().setMissileAvailable(true);
    }
}
