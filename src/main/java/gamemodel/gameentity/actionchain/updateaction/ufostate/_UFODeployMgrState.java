package gamemodel.gameentity.actionchain.updateaction.ufostate;

import gamemodel.gameentity.IGameEntity;

public abstract class _UFODeployMgrState {

    static _UFODeployMgrState PRELOAD = new UFODeployMgrPreloadState();
    static _UFODeployMgrState INIT = new UFODeployMgrInitialState();
    static _UFODeployMgrState RUNNING_RIGHT = new UFODeployMgrRunningRightState();
    static _UFODeployMgrState RUNNING_LEFT = new UFODeployMgrRunningLeftState();
    static _UFODeployMgrState RUNNING = RUNNING_RIGHT;
    static _UFODeployMgrState SPAWNING = new UFODeployMgrSpawningState();
    static _UFODeployMgrState COOLDOWN = new UFODeployMgrCooldownState();
    static _UFODeployMgrState SEEDING = new UFODeployMgrSeedingState();
    static _UFODeployMgrState DESTROY = new UFODeployMgrDestroyState();

    IGameEntity context;

    public abstract _UFODeployMgrState execute();

    public _UFODeployMgrState nextState(IGameEntity context) {

        if(this instanceof _UFODeployMgrRunningState) {
            DESTROY.context = context;
            return DESTROY;
        }

        else if(this instanceof UFODeployMgrSeedingState) {
            SPAWNING.context = context;
            return SPAWNING;
        }

        else if(this instanceof UFODeployMgrSpawningState) {
            RUNNING.context = context;
            return RUNNING;
        }

        else if (this instanceof UFODeployMgrInitialState) {
            SEEDING.context = context;
            return SEEDING;
        }

        else if(this instanceof UFODeployMgrDestroyState) {
            COOLDOWN.context = context;
            return COOLDOWN;
        }

        else if (this instanceof UFODeployMgrPreloadState) {
            INIT.context = context;
            return INIT;
        }

        else {
            INIT.context = context;
            return INIT;
        }

    }

    public void setRunningState(_UFODeployMgrRunningState state) {
        RUNNING = state;
    }

}
