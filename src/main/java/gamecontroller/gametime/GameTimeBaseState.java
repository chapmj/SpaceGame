package gamecontroller.gametime;

public class GameTimeBaseState extends GameTimeUpdateState {

    GameTimeUpdateState state;

    public GameTimeBaseState() {
        this.state = GameTimeUpdateState.RUNNING_STATE;
    }

    @Override
    public void update(GameTime instance) {
        state.update(instance);
    }

    public GameTimeUpdateState handle() {

        this.state = this.state.handle();
        this.state.dump();
        return this.state;
    }

    @Override
    public void dump() {
        System.out.println("GAME_TIME: BaseState holding:");
        state.dump();
    }
}
