package gamecontroller.gametime;

public abstract class GameTimeUpdateState {
    protected static final GameTimeUpdateState RUNNING_STATE = new GameTimeRunningState();
    protected static final GameTimeUpdateState STOPPED_STATE = new GameTimeStoppedState();
    public abstract void update(GameTime instance);
    public abstract GameTimeUpdateState handle();
    public abstract void dump();
}
