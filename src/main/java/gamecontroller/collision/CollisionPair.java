package gamecontroller.collision;

import utilext.RecursiveMethod;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames;

import java.util.ArrayList;
import java.util.List;

public class CollisionPair {
    private final RootNames rootA;
    private final RootNames rootB;
    private final ArrayList<_GameEntityComponent> aList = new ArrayList<>();
    private final ArrayList<_GameEntityComponent> bList = new ArrayList<>();

    public CollisionPair(RootNames rootA, RootNames rootB) {
        this.rootA = rootA;
        this.rootB = rootB;
    }

    public void collide() {
        var gameRootA = GameEntityMgr.getInstance().get(rootA);
        var gameRootB = GameEntityMgr.getInstance().get(rootB);

        if (gameRootA != null && gameRootB != null) {
            findCollision(gameRootA, gameRootB, aList); // set of leafs that intersects b
            findCollision(gameRootB, gameRootA, bList); // set of leafs that intersects a

            for(_GameEntityComponent a : aList) {
                for(_GameEntityComponent b : bList) {
                    if(intersect(a, b)) {
                        markIfColliding(a, b);
                    }
                }
            }

            aList.clear();
            bList.clear();
        }

    }

    @RecursiveMethod
    private void findCollision(_GameEntityComponent compA, _GameEntityComponent compB, List<_GameEntityComponent> list) {
        if(compA instanceof GameEntityLeaf) {
            list.add(compA);
        }

        if(intersect(compA, compB)) {
            for(_GameEntityComponent child : compA.getChildren()) {
                findCollision(child, compB, list);
            }
        }
    }

    private void markIfColliding(_GameEntityComponent leftComponent, _GameEntityComponent rightComponent) {

        var isColliding = (leftComponent instanceof GameEntityLeaf && rightComponent instanceof GameEntityLeaf);

        if (isColliding) {
            CollisionResolutionMgr.getInstance().add(leftComponent, rightComponent);
        }

    }

    private boolean intersect(_GameEntityComponent rootA, _GameEntityComponent rootB) {
        var rectA = rootA.getRect();
        var rectB = rootB.getRect();
        return rectA.intersects(rectB);
    }

    public boolean contains(_GameEntityComponent comp) {
        var gameRootA = GameEntityMgr.getInstance().get(rootA);
        var gameRootB = GameEntityMgr.getInstance().get(rootB);

        if(gameRootA == null || gameRootB == null) return false;

        return comp == gameRootA.getGameEntityComponent() || comp == gameRootB.getGameEntityComponent();
    }
}
