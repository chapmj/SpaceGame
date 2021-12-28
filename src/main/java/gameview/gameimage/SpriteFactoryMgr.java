package gameview.gameimage;

import gamemodel.gameentity.factory.FactorySprite;


import java.util.EnumMap;

public class SpriteFactoryMgr {

    private static SpriteFactoryMgr instance;
    private final EnumMap<SpriteName, FactorySprite> spriteFactories = new EnumMap<>(SpriteName.class);

    private SpriteFactoryMgr() { }

    public static SpriteFactoryMgr getInstance() {
        if (instance == null) {
            instance = new SpriteFactoryMgr();
            instance.init();
        }
        return instance;
    }

    public void init() {

        {
           var image = ImageMgr.get(ImageMgr.ImageName.SHIP_0);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.SHIP, 0.5, 0.5, image);
           add(SpriteName.SHIP, spriteFactory);
        }

        {
           var image0 = ImageMgr.get(ImageMgr.ImageName.CRAB_0);
           var image1 = ImageMgr.get(ImageMgr.ImageName.CRAB_1);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.CRAB, 0.5, 0.5, image0, image1);
           add(SpriteName.CRAB, spriteFactory);
        }

        {
           var image0 = ImageMgr.get(ImageMgr.ImageName.SQUID_0);
           var image1 = ImageMgr.get(ImageMgr.ImageName.SQUID_1);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.SQUID, 0.5, 0.5, image0, image1);
           add(SpriteName.SQUID, spriteFactory);
        }

        {
           var image0 = ImageMgr.get(ImageMgr.ImageName.OCTO_0);
           var image1 = ImageMgr.get(ImageMgr.ImageName.OCTO_1);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.OCTOPUS, 0.5, 0.5, image0, image1);
           add(SpriteName.OCTOPUS, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.UFO_0);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.UFO, 0.4, 0.4, image);
           add(SpriteName.UFO, spriteFactory);
        }

        {
            var image = ImageMgr.get(ImageMgr.ImageName.UFO_CRASH);
            FactorySprite spriteFactory = new FactorySprite(SpriteName.UFO_CRASH, 0.4, 0.4, image);
            add(SpriteName.UFO_CRASH, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.SHIELD_0);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.SHIELD, 0.5, 0.5, image);
           add(SpriteName.SHIELD, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.MISSILE_0);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.MISSILE, 1, 1, image);
           add(SpriteName.MISSILE, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.SHIELD_SPLAT);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.SPLAT, 0.5, 0.5, image);
           add(SpriteName.SPLAT, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.SHIELDCELL_BLANK);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.SHIELDCELL_BLANK, 1, 1, image);
           add(SpriteName.SHIELDCELL_BLANK, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.ALIEN_SPLAT);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.ALIEN_SPLAT, 0.4, 0.4, image);
           add(SpriteName.ALIEN_SPLAT, spriteFactory);
        }

        {
           var image = ImageMgr.get(ImageMgr.ImageName.MISSILE_SPLAT);
           FactorySprite spriteFactory = new FactorySprite(SpriteName.MISSILE_SPLAT, 0.5, 0.5, image);
           add(SpriteName.MISSILE_SPLAT, spriteFactory);
        }

        {
            var image0 = ImageMgr.get(ImageMgr.ImageName.SHIP_CRASH_0);
            var image1 = ImageMgr.get(ImageMgr.ImageName.SHIP_CRASH_1);
            FactorySprite spriteFactory = new FactorySprite(SpriteName.SHIP_CRASH, 0.5, 0.5, image0, image1);
            add(SpriteName.SHIP_CRASH, spriteFactory);
        }

        {
            var image0 = ImageMgr.get(ImageMgr.ImageName.BOMB_TUMBLE_0);
            var image1 = ImageMgr.get(ImageMgr.ImageName.BOMB_TUMBLE_1);
            var image2 = ImageMgr.get(ImageMgr.ImageName.BOMB_TUMBLE_2);
            var image3 = ImageMgr.get(ImageMgr.ImageName.BOMB_TUMBLE_3);
            FactorySprite spriteFactory = new FactorySprite(SpriteName.BOMB_TUMBLE, 0.5, 0.5, image0, image1, image2, image3);
            add(SpriteName.BOMB_TUMBLE, spriteFactory);
        }

        {
            var image0 = ImageMgr.get(ImageMgr.ImageName.BOMB_ZIGZAG_0);
            var image1 = ImageMgr.get(ImageMgr.ImageName.BOMB_ZIGZAG_1);
            var image2 = ImageMgr.get(ImageMgr.ImageName.BOMB_ZIGZAG_2);
            var image3 = ImageMgr.get(ImageMgr.ImageName.BOMB_ZIGZAG_3);
            FactorySprite spriteFactory = new FactorySprite(SpriteName.BOMB_ZIGZAG, 0.5, 0.5, image0, image1, image2, image3);
            add(SpriteName.BOMB_ZIGZAG, spriteFactory);
        }

        {
            var image0 = ImageMgr.get(ImageMgr.ImageName.BOMB_DRILL_0);
            var image1 = ImageMgr.get(ImageMgr.ImageName.BOMB_DRILL_1);
            var image2 = ImageMgr.get(ImageMgr.ImageName.BOMB_DRILL_2);
            var image3 = ImageMgr.get(ImageMgr.ImageName.BOMB_DRILL_3);
            FactorySprite spriteFactory = new FactorySprite(SpriteName.BOMB_DRILL, 0.5, 0.5, image0, image1, image2, image3);
            add(SpriteName.BOMB_DRILL, spriteFactory);
        }


    }

   public void add(SpriteName spriteName, FactorySprite spriteFactory) {
        this.spriteFactories.put(spriteName, spriteFactory);
    }

    public FactorySprite get(SpriteName spriteName) {

        var spriteFactory = spriteFactories.get(spriteName);
        return spriteFactory;
    }
}
