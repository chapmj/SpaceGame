package gamecontroller.gametime;

public class GameTimeRunningState extends GameTimeUpdateState {
    @Override
    public void update(GameTime instance) {
        int t = instance.tick + 1;
        instance.tick = (char) (t % Character.MAX_VALUE);
    }

    @Override
    public GameTimeUpdateState handle() {
        return GameTimeUpdateState.STOPPED_STATE;
    }

    @Override
    public void dump() {
        System.out.println("GAME_TIME: RUNNING_STATE");
    }
}
