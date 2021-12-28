package gamemodel.gameentity.gameentitycomponent;

import gamemodel.gameentity.IGameEntity;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

public class GameEntityMgr {

    static GameEntityMgr instance;
    final HashMap<RootNames, GameEntityRoot> roots; //holds boundaries, missiles, enemies, etc
    final ArrayList<_GameEntityComponent> postUpdateComponents;

    private GameEntityMgr() {
        this.roots = new HashMap<>();
        this.postUpdateComponents = new ArrayList<>();
    }

    public static GameEntityMgr getInstance() {
        if (instance == null) {
            instance = new GameEntityMgr();
        }
        return instance;
    }

    public void addLeaf(RootNames rootName, IGameEntity gameEntity) {
        var leaf = new GameEntityLeaf(gameEntity);
        add(rootName, leaf);
    }

    public void add(RootNames rootName, _GameEntityComponent gameEntity) {

        var root = this.roots.putIfAbsent(rootName, new GameEntityRoot(rootName));
        if(root == null) {
            root = roots.get(rootName);
        }
        root.add(gameEntity);
    }

    public void add(_GameEntityComponent gameEntity) {
        var rootName = gameEntity.getName();
        var root = this.roots.putIfAbsent(rootName, new GameEntityRoot(rootName));
        if(root == null) {
            root = roots.get(rootName);
        }
        root.add(gameEntity);

    }

    public GameEntityRoot get(RootNames rootName) {
        return this.roots.get(rootName);
    }

    public void update() {
        try {
            roots.forEach((key, root) -> root.update());

        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModification: Modification of GameEntity tree during GameEntity Update");
        }

        postUpdateComponents.forEach(this::add);
        postUpdateComponents.clear();
    }

    public Iterator<_GameEntityComponent> iterator() {

        var rootItr = roots.values().iterator();
        var itr = new GameEntityComponentIterator();

        while(rootItr.hasNext()) {
            var nextRoot = rootItr.next();
            if(nextRoot instanceof GameEntityRoot) {
                var root = (GameEntityRoot) nextRoot;
                var component = root.getGameEntityComponent();
                if(component != null) {
                    itr.add(component);
                }
            }
            else {
                try {
                    throw new Exception("Root tree has non-roots!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return itr;
    }

    public void addAfterUpdate(_GameEntityComponent gameEntity) {
        postUpdateComponents.add(gameEntity);
    }

    public void remove(RootNames rootName, IGameEntity gameEntity) {
        var root = (GameEntityRoot) roots.get(rootName);
        var gameEntityComponent = root.getGameEntityComponent();
        var itr = new GameEntityComponentIterator(gameEntityComponent);
        _GameEntityComponent markedGameEntity = null;

        while(itr.hasNext()) {

            var onGameEntity = itr.next();
            if(onGameEntity.matches(gameEntity)) {
                markedGameEntity = onGameEntity;
                break;
            }
        }

        if(markedGameEntity != null) {
            markedGameEntity.detachFromParent();
            markedGameEntity.destroy();
        }
    }

    public _GameEntityComponent getComponent(IGameEntity gameEntity)  {
        var name = gameEntity.getName();
        GameEntityRoot o = roots.get(name);
        GameEntityRoot root = null;
        if(o != null) {
            root = o;
        }
        var itr = new GameEntityComponentIterator(root.getGameEntityComponent());

        while(itr.hasNext()) {
            var component = itr.next();
            if(component.matches(gameEntity)) {
                return component;
            }
        }
        return null;
    }

    public void reset() {
        roots.clear();
        postUpdateComponents.clear();
    }

    public enum RootNames {
        ALIEN_ROOT, SHIP_ROOT, MISSILE_ROOT, SHIELD_ROOT, WALL_ROOT, UFO_ROOT, BOMB_ROOT, DEFAULT
    }
}
