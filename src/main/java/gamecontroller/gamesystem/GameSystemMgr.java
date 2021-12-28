package gamecontroller.gamesystem;

import java.util.HashMap;

public class GameSystemMgr
{
    private static GameSystemMgr instance;
    private final HashMap<GameSystemName, GameSystem> gameSystems;

    private GameSystemMgr(HashMap<GameSystemName, GameSystem> gameSystems) {
        this.gameSystems = gameSystems;
    }

    public static GameSystemMgr getInstance() {
        if (instance == null) {
            instance = new GameSystemMgr(new HashMap<>(4));
            instance.init();
        }
        return instance;
    }

    private void init() {
        instance.add(GameSystemName.MAIN_GAME, new GameSystem());
    }

    public void add(GameSystemName gameSystemName, GameSystem gameSystem) {
        this.gameSystems.put(gameSystemName, gameSystem);
    }

    public GameSystem get(GameSystemName gameSystemName) {
        var gameSystem = gameSystems.get(gameSystemName);
        return gameSystem;
    }

    public enum GameSystemName {
        MAIN_GAME
    }
}
