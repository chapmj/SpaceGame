package gamecontroller;

import java.time.Instant;
import java.util.Random;

public class RandomMgr {

    private final Random random = new Random();

    private static RandomMgr instance;


    public static RandomMgr getInstance() {
        if (instance == null) {
            instance = new RandomMgr();
            instance.init();
        }
        return instance;
    }

    private void init() {
        var epochTime = Instant.now().getEpochSecond();
        random.setSeed(epochTime);
    }

    public void update() { }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public void reset() { }

}
