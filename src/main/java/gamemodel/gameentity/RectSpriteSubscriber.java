package gamemodel.gameentity;

import gamemodel.gamerect.IRect;

public class RectSpriteSubscriber implements ISpriteSubscriber {

    private IRect rect;

    public RectSpriteSubscriber(IRect rect) {
        this.rect = rect;
    }

    @Override
    public IRect getRect() {
        return rect;
    }
}
