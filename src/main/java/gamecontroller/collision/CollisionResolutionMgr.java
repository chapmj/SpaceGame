package gamecontroller.collision;

import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollisionResolutionMgr {
    private static CollisionResolutionMgr instance;

    List<_GameEntityComponent> pairs = new ArrayList<>();

    private CollisionResolutionMgr() {
    }

    public static CollisionResolutionMgr getInstance() {
        if (instance == null) {
            instance = new CollisionResolutionMgr();
        }
        return instance;
    }

    public void reset() {
        this.pairs.clear();
    }

    public void add(_GameEntityComponent compA, _GameEntityComponent compB) {
        Objects.requireNonNull(compA);
        Objects.requireNonNull(compB);

        pairs.add(compA);
        pairs.add(compB);
    }

    public void update() {
        for(int i = 0; i < pairs.size() - 1; i += 2) {
            var current = i;
            var next = i + 1;

            var compA = pairs.get(current);
            var compB = pairs.get(next);

            compA.collide(compB);
            compB.collide(compA);
        }

        pairs.clear();
    }
}
