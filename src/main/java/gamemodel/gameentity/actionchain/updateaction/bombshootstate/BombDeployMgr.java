package gamemodel.gameentity.actionchain.updateaction.bombshootstate;

import gamecontroller.RandomMgr;

import java.util.ArrayList;
import java.util.Random;

public class BombDeployMgr {

    private static BombDeployMgr instance;
    private ArrayList<BombUpdateAction> bombSubscribers;
    Random random = new Random();
    int COOLDOWN_FLOOR = 40;
    int cooldown = COOLDOWN_FLOOR;
    private int pauseTimer = 0;


    private BombDeployMgr(ArrayList<BombUpdateAction> bombSubscribers) {
        this.bombSubscribers = bombSubscribers;
    }

    public static BombDeployMgr getInstance() {
        if (instance == null) {
            instance = new BombDeployMgr(new ArrayList<>(4));
        }
        return instance;
    }

    public void update() {
        if(pauseTimer == 0) {

            cooldown -= 1;

            if (bombSubscribers.size() > 0 && cooldown <= 0) {

                var randSrc = RandomMgr.getInstance();
                var randNum = randSrc.nextInt(bombSubscribers.size());
                //var randIndex = randNum % (bombSubscribers.size());
                var randIndex = randNum;

                if (cooldown == 0) {
                    var bombAction = bombSubscribers.get(randIndex);
                    bombAction.fire();
                    cooldown = COOLDOWN_FLOOR + randNum % 40;
                }

            }
        }
        else {
            pauseTimer -= 1;
        }
    }

    public void subscribe(BombUpdateAction bombUpdateAction) {
        bombSubscribers.add(bombUpdateAction);

    }

    public void unsubscribe(BombUpdateAction bombUpdateAction) {
        bombSubscribers.remove(bombUpdateAction);
    }

    public void reset() {
        bombSubscribers.clear();
        COOLDOWN_FLOOR = 40;
        cooldown = COOLDOWN_FLOOR;
        pauseTimer = 0;
    }

    public void pause(int time) {
        this.pauseTimer = time;
    }
}
