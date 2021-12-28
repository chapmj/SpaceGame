package gamemodel.gameentity.actionchain.updateaction.ufostate;

public class UFODeployMgr {
    _UFODeployMgrState state = _UFODeployMgrState.PRELOAD;

    private static UFODeployMgr instance;
    int pauseTimer = 0;

    private UFODeployMgr() {
    }

    public static UFODeployMgr getInstance() {
        if (instance == null) {
            instance = new UFODeployMgr();
        }
        return instance;
    }

    public void update() {
        if(pauseTimer == 0) {
            state = state.execute();
        }
        else {
            pauseTimer -= 1;
        }
    }

    public void reset() {
        state = state.nextState(null);
    }

    public void resetService() {
        state = _UFODeployMgrState.PRELOAD;
    }
}
