package gameview.text;


import java.util.EnumMap;

public class TextMgr {

    private static TextMgr instance;
    private EnumMap<GameText, String> gameStrings = new EnumMap<>(GameText.class);
    private EnumMap<GameText, TextPosition> textPositions = new EnumMap<>(GameText.class);
    public double textScale = 0.5;
    private TextPosition textPositionDefault = new TextPosition(0, 0);

    private TextMgr() { }

    public static TextMgr getInstance() {
        if (instance == null) {
            instance = new TextMgr();
            instance.init();
        }
        return instance;
    }

    public void init() {
    }

    public void add(GameText gameText, String s, TextPosition textPosition) {
        gameStrings.put(gameText, s);
        textPositions.put(gameText, textPosition);
    }

    public String get(GameText gameText) {
        return gameStrings.get(gameText);
    }

    public void draw() {

        for(GameText gameText : gameStrings.keySet()) {

            var gameString = gameStrings.getOrDefault(gameText, "");
            var textPosition = textPositions.getOrDefault(gameText, textPositionDefault);

            GlyphDrawer.getInstance().draw(gameString, textPosition.x(), textPosition.y(), textScale);
        }

    }

    public void clear() {
        gameStrings.clear();
        textPositions.clear();
    }

    public void dump() {
    }

    public enum GameText {
        SCORE, HI_SCORE, LIVES, CREDITS, GAME_OVER, TITLE
    }
}
