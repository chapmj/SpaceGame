package gamemodel.gameentity.factory;

import gameview.gameimage.*;

public class FactorySprite {

    private final Image[] images;
    private double sx;
    private double sy;
    private SpriteName spriteName;

    public FactorySprite(SpriteName spriteName, Image... images) {
        this.spriteName = spriteName;
        this.images = images;
        this.sx = 0;
        this.sy = 0;
    }

    public FactorySprite(SpriteName spriteName, double sx, double sy, Image... images) {
        this.spriteName = spriteName;
        this.images = images;
        this.sx = sx;
        this.sy = sy;
    }

    public _Sprite create() {
        _Sprite newSprite = null;

        if (images.length == 1) {
            newSprite = createGameSprite(this.sx, this.sy);
        }
        else if (images.length > 1) {
            newSprite = createAnimatedSprite(this.sx, this.sy);
        }
        return newSprite;

    }
    public _Sprite create(double scaleX, double scaleY) {
        _Sprite newSprite = null;

        if (images.length == 1) {
            newSprite = createGameSprite(scaleX, scaleY);
        }
        else if (images.length > 1) {
            newSprite = createAnimatedSprite(scaleX, scaleY);
        }
        return newSprite;
    }

    public _Sprite createGameSprite(double scaleX, double scaleY) {
        var imgset = new ImageSet(images[0].x(), images[0].y(), images[0].w(), images[0].h());


        for (int i = 1; i < images.length; i++) {
            imgset.add(images[i].x(), images[i].y(), images[i].w(), images[i].h());
        }

        var sprite = new GameSprite(spriteName, imgset, 0, 0, scaleX, scaleY);
        return sprite;
    }

    public _Sprite createAnimatedSprite(double scaleX, double scaleY) {
        var imgset = new ImageSet(images[0].x(), images[0].y(), images[0].w(), images[0].h());


        for (int i = 1; i < images.length; i++) {
            imgset.add(images[i].x(), images[i].y(), images[i].w(), images[i].h());
        }

        var sprite = new GameSprite(spriteName, imgset, 0, 0, scaleX, scaleY);
        return sprite;
    }

}
