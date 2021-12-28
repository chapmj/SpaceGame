package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.command._ICommand;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class CommandExecutorAction extends _GameEntityActionChain {

    _ICommand command;
    public CommandExecutorAction(_ICommand command) {
        this.command = command;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        command.execute();
    }

}
