package gameview.gameimage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;

public class SpriteMgr {

    private static SpriteMgr instance;
    private final EnumMap<SpriteName, _Sprite> namedSprites = new EnumMap<>(SpriteName.class);
    private final EnumMap<SpriteName, List<_Sprite>> spritePriorityTree = new EnumMap<>(SpriteName.class);

    private SpriteMgr() { }

    public static SpriteMgr getInstance() {
        if (instance == null) {
            instance = new SpriteMgr();
        }
        return instance;
    }

   //sprite add adds to draw list
   public void add(_Sprite gameSprite) {

       var spriteName = gameSprite.getName();
        List<_Sprite> spriteList = this.spritePriorityTree.getOrDefault(spriteName, new ArrayList<>());
        spriteList.add(gameSprite);
        spritePriorityTree.put(spriteName, spriteList);
    }

    public _Sprite get(SpriteName spriteName) {

        var sprite = namedSprites.get(spriteName);
        return sprite;
    }

    public _Sprite create(SpriteName spriteName) {
        var spriteFactoryMgr = SpriteFactoryMgr.getInstance();
        var spriteFactory = spriteFactoryMgr.get(spriteName);
        var sprite = spriteFactory.create();
        return sprite;
    }

    public Collection<_Sprite> getAll() {
        var flatListOfSprites = new ArrayList<_Sprite>();
        for(List<_Sprite>spriteList: spritePriorityTree.values()) {
            flatListOfSprites.addAll(spriteList);
        }
        return flatListOfSprites;
    }
    public void reset() {
        spritePriorityTree.values().forEach(List::clear);
    }

    public void remove(_Sprite splat) {
        var spriteName = splat.getName();
        var sprites = spritePriorityTree.get(spriteName);
        sprites.remove(splat);

    }
}
