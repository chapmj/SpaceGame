package gameview.gameimage;

import java.util.EnumMap;

public class ImageMgr {

    /* Global dictionary of images */

    private static ImageMgr instance;
    private javafx.scene.image.Image spritesheet;
    private EnumMap<ImageName, Image> images = new EnumMap<>(ImageName.class);

    private ImageMgr() {
    }

    public static ImageMgr getInstance() {
        if (instance == null) {
            instance = new ImageMgr();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.spritesheet = new javafx.scene.image.Image("spritesheet.png");
        images.put(ImageName.CRAB_0, new Image(387, 581, 89, 64));
        images.put(ImageName.CRAB_1, new Image(491, 581, 89, 64));
        images.put(ImageName.GAMEOVER_CRAB_0, new Image(387, 581, 89, 64));
        images.put(ImageName.GAMEOVER_CRAB_1, new Image(491, 581, 89, 64));
        images.put(ImageName.OCTO_0, new Image(374, 688, 96, 64));
        images.put(ImageName.OCTO_1, new Image(488, 689, 96, 64));
        images.put(ImageName.GAMEOVER_OCTO_0, new Image(374, 688, 96, 64));
        images.put(ImageName.GAMEOVER_OCTO_1, new Image(488, 689, 96, 64));
        images.put(ImageName.SQUID_0, new Image(400, 477, 64, 64));
        images.put(ImageName.SQUID_1, new Image(502, 478, 64, 64));
        images.put(ImageName.GAMEOVER_SQUID_0, new Image(400, 477, 64, 64));
        images.put(ImageName.GAMEOVER_SQUID_1, new Image(502, 478, 64, 64));
        images.put(ImageName.UFO_0, new Image(385, 782, 200, 79));
        images.put(ImageName.UFO_CRASH, new Image(562, 868, 246, 82));
        images.put(ImageName.GAMEOVER_UFO_0, new Image(562, 878, 246, 82));
        images.put(ImageName.MISSILE_0, new Image(772, 351, 4, 10));
        images.put(ImageName.GAMEOVER_MISSILE_0, new Image(415, 279, 2, 10));
        images.put(ImageName.SHIP_0, new Image(357, 1160, 104, 70));//keep old ship
        images.put(ImageName.SHIP_CRASH_0, new Image(548, 1296, 120, 64));//crash frame 0
        images.put(ImageName.SHIP_CRASH_1, new Image(541, 1368, 120, 64));//crash frame 1
        images.put(ImageName.GAMEOVER_SHIP_0, new Image(543, 1334, 110, 80));
        images.put(ImageName.SHIELD_0, new Image(400, 964, 177, 129 ));
        images.put(ImageName.GAMEOVER_SHIELD_0, new Image(400, 964, 177, 129));
        images.put(ImageName.SHIELD_SPLAT, new Image(372,878, 77, 73));
        images.put(ImageName.SHIELDCELL_BLANK, new Image(0, 0, 20, 20));
        images.put(ImageName.ALIEN_SPLAT, new Image(479, 1139, 106, 67));
        images.put(ImageName.MISSILE_SPLAT, new Image(377, 1372, 73, 73));

        images.put(ImageName.BOMB_TUMBLE_0, new Image(643, 351,20,  48));
        images.put(ImageName.BOMB_TUMBLE_1, new Image(673, 351,20,  48));
        images.put(ImageName.BOMB_TUMBLE_2, new Image(704, 351,20,  48));
        images.put(ImageName.BOMB_TUMBLE_3, new Image(734, 351,20,  48));

       //643, 403
        images.put(ImageName.BOMB_ZIGZAG_0, new Image(643, 403,20,  48));
        images.put(ImageName.BOMB_ZIGZAG_1, new Image(685, 403,20,  48));
        images.put(ImageName.BOMB_ZIGZAG_2, new Image(713, 403,20,  48));
        images.put(ImageName.BOMB_ZIGZAG_3, new Image(747, 403,20,  48));

        images.put(ImageName.BOMB_DRILL_0, new Image(643, 296,22,  48));
        images.put(ImageName.BOMB_DRILL_1, new Image(671, 296,22,  48));
        images.put(ImageName.BOMB_DRILL_2, new Image(702, 296,22,  48));
        images.put(ImageName.BOMB_DRILL_3, new Image(732, 296,22,  48));

    }


// --Commented out by Inspection START (2021-12-27 23:50):
//    public void add(ImageName imageName, Image image) {
//        this.images.put(imageName, image);
//    }
// --Commented out by Inspection STOP (2021-12-27 23:50)

    private Image getImage(ImageName imageName) {
        var image = images.get(imageName);
        return image;
    }

    public javafx.scene.image.Image getSpritesheet() {
        return this.spritesheet;
    }

    public static Image get(ImageName imageName) {
        return getInstance().getImage(imageName);
    }

    public enum ImageName {
        CRAB_0, CRAB_1, GAMEOVER_CRAB_0, GAMEOVER_CRAB_1,
        OCTO_0, OCTO_1, GAMEOVER_OCTO_0, GAMEOVER_OCTO_1,
        UFO_0, GAMEOVER_UFO_0, GAMEOVER_UFO_1,
        SQUID_0, SQUID_1, GAMEOVER_SQUID_0, GAMEOVER_SQUID_1,
        MISSILE_0, GAMEOVER_MISSILE_0,
        SHIP_0, GAMEOVER_SHIP_0, GAMEOVER_SHIP_1, SHIP_CRASH_0, SHIP_CRASH_1,
        SHIELD_0, GAMEOVER_SHIELD_0, SHIELD_SPLAT,
        ALIEN_SPLAT, MISSILE_SPLAT, SHIELDCELL_BLANK,
        BOMB_TUMBLE_0, BOMB_TUMBLE_1, BOMB_TUMBLE_2, BOMB_TUMBLE_3,
        BOMB_ZIGZAG_0, BOMB_ZIGZAG_1, BOMB_ZIGZAG_2, BOMB_ZIGZAG_3,
        BOMB_DRILL_0, BOMB_DRILL_1, BOMB_DRILL_2, BOMB_DRILL_3, UFO_CRASH

    }
}
