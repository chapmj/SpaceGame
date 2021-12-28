package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.RectSpriteSubscriber;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gamerect.WRect;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import utilext.Geometry;

public class CreatePermaSplatCentered extends _GameEntityActionChain {
    IGameEntity gameEntity;

    public CreatePermaSplatCentered(IGameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var splat = SpriteMgr.getInstance().create(SpriteName.SPLAT);
        SpriteMgr.getInstance().add(splat);

        var cx = gameEntity.getRect().x();
        var cy = gameEntity.getRect().y();
        var cw = gameEntity.getRect().w();
        var ch = gameEntity.getRect().h();

        var gameEntityCenter = Geometry.getCenter(cx, cy, cw, ch);

        var kw = splat.getW();
        var kh = splat.getH();

        var splatCorner = Geometry.getCorner(gameEntityCenter[0], gameEntityCenter[1], kw, kh);

        var spriteSubscriber = new RectSpriteSubscriber(new WRect(splatCorner[0], splatCorner[1], kw, kh));
        splat.attach(spriteSubscriber);

    }

}
