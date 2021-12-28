package gamemodel.gameentity.actionchain.updateaction.ufostate;

public class UFODeployMgrDestroyState extends _UFODeployMgrState {
    @Override
    public _UFODeployMgrState execute() {

        if(context != null) {
            context.destroy();
        }
        return nextState(null);
    }
}
