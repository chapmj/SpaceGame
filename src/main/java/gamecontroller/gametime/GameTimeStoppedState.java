package gamecontroller.gametime;

public class GameTimeStoppedState extends GameTimeUpdateState {
    @Override
    public void update(GameTime instance) { }

    @Override
    public GameTimeUpdateState handle() {
        return GameTimeUpdateState.RUNNING_STATE;
    }

    @Override
    public void dump() {
        System.out.println("GAME_TIME: RUNNING_STATE");
    }
}
