package gamecontroller.gameroundinitializer;

import gamecontroller.collision.CollisionMgr;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.*;

public class CollisionInitializer extends _Initializer {
    @Override
    public void init() {
        CollisionMgr.getInstance().add(ALIEN_ROOT, SHIELD_ROOT);
        CollisionMgr.getInstance().add(ALIEN_ROOT, SHIP_ROOT);
        CollisionMgr.getInstance().add(ALIEN_ROOT, WALL_ROOT);
        CollisionMgr.getInstance().add(SHIP_ROOT, WALL_ROOT);
        CollisionMgr.getInstance().add(MISSILE_ROOT, WALL_ROOT);
        CollisionMgr.getInstance().add(MISSILE_ROOT, ALIEN_ROOT);
        CollisionMgr.getInstance().add(MISSILE_ROOT, SHIELD_ROOT);
        CollisionMgr.getInstance().add(MISSILE_ROOT, UFO_ROOT);
        CollisionMgr.getInstance().add(MISSILE_ROOT, BOMB_ROOT);
        CollisionMgr.getInstance().add(BOMB_ROOT, WALL_ROOT);
        CollisionMgr.getInstance().add(BOMB_ROOT, SHIELD_ROOT);
        CollisionMgr.getInstance().add(BOMB_ROOT, SHIP_ROOT);
    }
}
