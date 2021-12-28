package gamecontroller.gametime;

public class GameTime {
    public static GameTime instance;
    char tick;

    private GameTime() {
       this.tick = 0;
    }

    public static GameTime getInstance() {
        if(instance == null) {
            instance = new GameTime();
        }
        return instance;
    }

    public static void init() {
        GameTime.getInstance();
    }

    public void update() {
        int t = instance.tick + 1;
        if(instance.tick == Character.MAX_VALUE) {
            System.out.println("Rollover");
        }
        instance.tick = (char) (t % Character.MAX_VALUE);
    }

    public int now() {
        return tick;
    }

    public int getTimeWithDelay(char delay) {
        int t = now() + delay;
        return t % Character.MAX_VALUE;
    }

    public int getTimeNext() {
        int t = now() + 1;
        return t % Character.MAX_VALUE;
    }

}
