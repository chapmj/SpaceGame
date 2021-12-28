package gamecontroller.command;

import gamecontroller.HeatMgr;

public class RefreshHeatCommand extends _GameEntityCommand {

    public RefreshHeatCommand() { }

    @Override
    public void execute() {
        HeatMgr.getInstance().refreshHeat();
        executeNext();
    }

}
