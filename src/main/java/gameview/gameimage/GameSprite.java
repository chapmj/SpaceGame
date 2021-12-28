package gameview.gameimage;

/**
 * Animate-able sprite images and rectangle
 */
public class GameSprite extends _Sprite
{
    private final _GameImage image;
    private double x;
    private double y;
    private double sx;
    private double sy;
    private SpriteName spriteName;

    public GameSprite(SpriteName spriteName, _GameImage image, double x, double y, double sx, double sy) {
        this.spriteName = spriteName;
        this.image = image;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    public _GameImage getImage() {
        return this.image;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getW() {
        return image.w() * sx;
    }

    @Override
    public double getH() {
        return image.h() * sy;
    }

    public double getSx() {
        return this.sx;
    }

    public double getSy() {
        return this.sy;

    }


    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    public void nextFrame() {
        this.image.handle();
    }

    @Override
    public SpriteName getName() {
        return this.spriteName;
    }
}
