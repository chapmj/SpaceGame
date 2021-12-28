package gamemodel.gameentity.actionchain.updateaction.shootstate;

import gamecontroller.GameStats;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.factory.FactoryMissile;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.sound.SoundMgr;
import utilext.Geometry;

public class ShipShootFiringState extends _ShipShootState {
    @Override
    public void update(IGameEntity gameEntity) {

        if(GameStats.getInstance().isMissileAvailable()) {
            var missile = FactoryMissile.getInstance().create();
            var gx = (int) gameEntity.getRect().x();
            var gy = (int) gameEntity.getRect().y();
            var gw = (int) gameEntity.getRect().w();

            var mw = missile.getRect().w();

            var shipMidpoint = Geometry.calcMidpoint(gx, gx + gw);
            var missileHalfWidth = 0.5 * mw;

            var newX = shipMidpoint - missileHalfWidth;
            var newY = gy + 1;
            var missileRect = missile.getRect();
            missileRect.positionRect(newX, newY);

            var missileLeaf = new GameEntityLeaf(missile);
            GameEntityMgr.getInstance().addAfterUpdate(missileLeaf);
            SoundMgr.playInterrupting(SoundMgr.SoundName.MISSILE_SHOOT, 0.05);
            GameStats.getInstance().setMissileAvailable(false);
            var shotsFired = GameStats.getInstance().getShotsFired() + 1;
            GameStats.getInstance().setShotsFired(shotsFired);
        }
    }

    @Override
    public void dump() {
        System.out.println("ShootState: FIRING");
    }
}
