package gameview.gameimage;

import gamemodel.gamerect.IRect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RectMgr {

    private static RectMgr instance;
    private final ArrayList<IRect> rects = new ArrayList<>();

    private RectMgr() { }

    public static RectMgr getInstance() {
        if (instance == null) {
            instance = new RectMgr();
        }
        return instance;
    }

   public void add(IRect rect) {
        rects.add(rect);
    }

   public void remove(IRect rect) {
       rects.remove(rect);
   }

    public _Sprite create(SpriteName spriteName) {
        var spriteFactoryMgr = SpriteFactoryMgr.getInstance();
        var spriteFactory = spriteFactoryMgr.get(spriteName);
        var sprite = spriteFactory.create();
        return sprite;
    }

    public Collection<IRect> getAll() {
        return Collections.unmodifiableCollection(rects);
    }

    public void reset() {
        rects.clear();
    }
}
