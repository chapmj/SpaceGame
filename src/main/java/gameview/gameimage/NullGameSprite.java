package gameview.gameimage;

import gamemodel.gameentity.ISpriteSubscriber;

/**
 * Animate-able sprite images and rectangle
 */
public class NullGameSprite extends _Sprite
{

    public NullGameSprite(_GameImage image, double x, double y, double sx, double sy) { }

    public _GameImage getImage() {
        return ImageMgr.get(ImageMgr.ImageName.MISSILE_SPLAT);
    }

    public double getX() {
        return 0;
    }

    public double getY() {
        return 0;
    }

    public double getW() {
        return 0;
    }

    public double getH() {
        return 0;
    }

    public void setX(double x) { }

    public void setY(double y) { }

    public void attach(ISpriteSubscriber spriteSubscriber) { }

    public void detach(ISpriteSubscriber spriteSubscriber) { }

    public void initialize() { }

    public void reset() { }

    public void clearSubscriptions() { }

    public boolean hasNext() {
        return false;
    }

    public void updateNext() { }

    public void nextFrame() { }

    @Override
    public SpriteName getName() {
        return null;
    }
}
