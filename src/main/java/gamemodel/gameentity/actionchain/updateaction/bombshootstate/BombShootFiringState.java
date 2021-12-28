package gamemodel.gameentity.actionchain.updateaction.bombshootstate;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.factory.FactoryBomb;
import gamemodel.gameentity.gameentitycomponent.GameEntityLeaf;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import utilext.Geometry;

public class BombShootFiringState extends _BombShootState {
    @Override
    public void update(IGameEntity gameEntity) {
        var bomb = FactoryBomb.getInstance().create();
        var gx = (int)gameEntity.getRect().x();
        var gy= (int)gameEntity.getRect().y();
        var gw = (int)gameEntity.getRect().w();
        var gh = (int)gameEntity.getRect().h();

        var bw = bomb.getRect().w();

        var alienMidpoint = Geometry.calcMidpoint(gx, gx + gw);

        var newX = alienMidpoint - (0.5 * bw);
        var newY = gy + gh + 32;
        var bombRect = bomb.getRect();
        bombRect.positionRect(newX, newY);

        var bombLeaf = new GameEntityLeaf(bomb);
        GameEntityMgr.getInstance().addAfterUpdate(bombLeaf);
    }

    @Override
    public void dump() {

    }
}
