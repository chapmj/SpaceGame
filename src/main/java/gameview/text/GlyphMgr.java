package gameview.text;

import gameview.gameimage.Image;

import java.util.HashMap;

public class GlyphMgr {

    private static GlyphMgr instance;
    private HashMap<Character, Image> images = new HashMap<>();
    private javafx.scene.image.Image fontsheet;

    private GlyphMgr() { }

    public static GlyphMgr getInstance() {
        if (instance == null) {
            instance = new GlyphMgr();
            instance.init();
        }
        return instance;
    }

    public void init() {
        this.fontsheet = new javafx.scene.image.Image("fontsheet.png");
        //row 1
        images.put('A', new Image(0, 60, 48, 48));
        images.put('B', new Image(60, 60, 48, 48));
        images.put('C', new Image(120, 60, 48, 48));
        images.put('D', new Image(180, 60, 48, 48));
        images.put('E', new Image(240, 60, 48, 48));
        images.put('F', new Image(300, 60, 48, 48));
        images.put('G', new Image(360, 60, 48, 48));
        images.put('H', new Image(420, 60, 48, 48));
        //row 2
        images.put('I', new Image(0,  120, 48, 48));
        images.put('J', new Image(60, 120, 48, 48));
        images.put('K', new Image(120, 120, 48, 48));
        images.put('L', new Image(180, 120, 48, 48));
        images.put('M', new Image(240, 120, 48, 48));
        images.put('N', new Image(300, 120, 48, 48));
        images.put('O', new Image(360, 120, 48, 48));
        images.put('P', new Image(420, 120, 48, 48));
        //row 3
        images.put('Q', new Image(0, 180, 48, 48));
        images.put('R', new Image(60,  180, 48, 48));
        images.put('S', new Image(120, 180, 48, 48));
        images.put('T', new Image(180, 180, 48, 48));
        images.put('U', new Image(240, 180, 48, 48));
        images.put('V', new Image(300, 180, 48, 48));
        images.put('W', new Image(360, 180, 48, 48));
        images.put('X', new Image(420, 180, 48, 48));
        //row 4
        images.put('Y', new Image(0, 240, 48, 48));
        images.put('Z', new Image(60,  240, 48, 48));
        images.put('0', new Image(120, 240, 48, 48));
        images.put('1', new Image(180, 240, 48, 48));
        images.put('2', new Image(240, 240, 48, 48));
        images.put('3', new Image(300, 240, 48, 48));
        images.put('4', new Image(360, 240, 48, 48));
        images.put('5', new Image(420, 240, 48, 48));
        //row 5
        images.put('6', new Image(0, 300, 48, 48));
        images.put('7', new Image(60,  300, 48, 48));
        images.put('8', new Image(120, 300, 48, 48));
        images.put('9', new Image(180, 300, 48, 48));
        images.put('<', new Image(240, 300, 48, 48));
        images.put('>', new Image(300, 300, 48, 48));
        images.put('=', new Image(360, 300, 48, 48));
        images.put('*', new Image(420, 300, 48, 48));
        //row 6
        images.put('?', new Image(0, 360, 48, 48));
        images.put('-', new Image(60,  360, 48, 48));
        images.put(' ', new Image(120,  360, 48, 48));
        images.put('~', new Image(180,  360, 48, 48));
    }

    public void add(Character character, Image image) {
        this.images.put(character, image);
    }

    public Image get(Character character) {
        var defaultImage = images.get('~');
        var image = images.getOrDefault(character, defaultImage);
        return image;
    }

    public javafx.scene.image.Image getFontsheet() {
        return this.fontsheet;
    }

}
