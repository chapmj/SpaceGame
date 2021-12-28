package gamecontroller.collision;

import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

import java.util.ArrayList;

public class CollisionMgr {
    private static CollisionMgr instance;
    private final ArrayList<CollisionPair> collisionPairs;

    public CollisionMgr(ArrayList<CollisionPair> collisionPairs) {
        this.collisionPairs = collisionPairs;
    }


    public static CollisionMgr getInstance() {
        if (instance == null) {
            instance = new CollisionMgr(new ArrayList<>());
        }
        return instance;
    }

    public void update() {
        for(CollisionPair pair : collisionPairs) {
            pair.collide();
        }

    }

    public void add(GameEntityMgr.RootNames compA, GameEntityMgr.RootNames compB) {
        var pair = new CollisionPair(compA, compB);
        collisionPairs.add(pair);
    }

    public void remove(_GameEntityComponent comp) {

        CollisionPair pairMarkedForRemoval = null;

        for(CollisionPair pair : collisionPairs) {
            if(pair.contains(comp)) {
                pairMarkedForRemoval = pair;
            }
        }

        if(pairMarkedForRemoval != null) {
            collisionPairs.remove(pairMarkedForRemoval);
        }

    }

    public void reset() {
        collisionPairs.clear();
    }
}
