package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.TimedCommandMgr;
import gamecontroller.command.TimedCommand;
import gamecontroller.command._ICommand;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class TimedCommandExecutorAction extends _GameEntityActionChain {

    _ICommand command;
    int delay;

    public TimedCommandExecutorAction(_ICommand command, int delay) {
        this.command = command;
        this.delay = delay;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var runAt = GameTime.getInstance().getTimeWithDelay((char)delay);
        var tc = new TimedCommand(command, runAt);
        TimedCommandMgr.getInstance().add(tc);
    }

}
