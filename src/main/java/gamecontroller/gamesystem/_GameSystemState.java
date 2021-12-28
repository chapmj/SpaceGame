package gamecontroller.gamesystem;

@SuppressWarnings("StaticInitializerReferencesSubClass")
public abstract class _GameSystemState {
    public static _GameSystemState STOPPED_STATE = new GameSystemStoppedState();
    public static _GameSystemState RUNNING_STATE = new GameSystemRunningState();
    public static _GameSystemState CLEANUP_STATE = new GameSystemCleanupState();
    public static _GameSystemState INITIALIZING_STATE = new GameSystemInitializingState();
    public static _GameSystemState TITLE_MENU_STATE = new GameSystemTitleMenuState();
    public static _GameSystemState GAME_OVER_STATE = new GameSystemGameOverState();
    public static _GameSystemState NEXT_ROUND_STATE = new GameSystemNextRoundState();
    public static _GameSystemState NEW_ROUND_ANIMATION_STATE = new GameSystemNewRoundAnimationState();
    public static _GameSystemState SHIP_HIT_STATE = new GameSystemShipHitState();
    public abstract void _update();

    public void update() {
        _update();
        handle();
    }

    protected void handle() { }

}
