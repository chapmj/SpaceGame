package gamecontroller.command;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.RectSpriteSubscriber;
import gamemodel.gamerect.WRect;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import utilext.Geometry;

public class CreatePermaSplatCenteredCmd implements _ICommand {
    private final SpriteName splatSpriteName;
    private final double offsetX;
    private final double offsetY;
    IGameEntity gameEntity;

    public CreatePermaSplatCenteredCmd(IGameEntity gameEntity, SpriteName splatSpriteName, double offsetX, double offsetY) {
        this.gameEntity = gameEntity;
        this.splatSpriteName = splatSpriteName;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void execute() {
        var splat = SpriteMgr.getInstance().create(splatSpriteName);
        SpriteMgr.getInstance().add(splat);

        var cx = gameEntity.getRect().x();
        var cy = gameEntity.getRect().y();
        var cw = gameEntity.getRect().w();
        var ch = gameEntity.getRect().h();

        var gameEntityCenter = Geometry.getCenter(cx, cy, cw, ch);

        var kw = splat.getW();
        var kh = splat.getH();

        var splatCorner = Geometry.getCorner(gameEntityCenter[0], gameEntityCenter[1], kw, kh);

        var spriteSubscriber = new RectSpriteSubscriber(new WRect(splatCorner[0] + offsetX, splatCorner[1] + offsetY, kw, kh));
        splat.attach(spriteSubscriber);
    }

}
