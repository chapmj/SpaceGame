package gamemodel.gameentity.actionchain.updateaction.ufostate;

public class UFODeployMgrPreloadState extends _UFODeployMgrState {
    final int PRELOAD_WAIT = 40 * 10;
    int WAIT_TIME = 0;
    @Override
    public _UFODeployMgrState execute() {

        WAIT_TIME += 1;

        if (PRELOAD_WAIT == WAIT_TIME) {
            WAIT_TIME = 0;
            return nextState(null);
        }

        return this;
    }
}
