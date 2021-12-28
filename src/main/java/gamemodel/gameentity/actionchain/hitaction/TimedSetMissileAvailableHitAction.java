package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.TimedCommandMgr;
import gamecontroller.command.MissileAvailableCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class TimedSetMissileAvailableHitAction extends _GameEntityActionChain {

    public TimedSetMissileAvailableHitAction() { }

    public void _doAction(IGameEntity gameEntity) {

        var duration = (char)2;
        var runAtTime = GameTime.getInstance().getTimeWithDelay(duration);
        var tc = new TimedCommand(new MissileAvailableCommand(), runAtTime);
        TimedCommandMgr.getInstance().add(tc);

    }
}
