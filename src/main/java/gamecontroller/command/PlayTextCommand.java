package gamecontroller.command;

import gamecontroller.TimedCommandMgr;
import gamecontroller.gametime.GameTime;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class PlayTextCommand implements _ICommand {

    private final TextMgr.GameText text;
    private final char charDelay;
    private final double x;
    private final double y;
    private final String rawText;
    private int textIndexAt = 0;

    public PlayTextCommand(TextMgr.GameText gameText, String rawText, char charDelay, double x, double y) {
        this.text = gameText;
        this.charDelay = charDelay;
        this.x = x;
        this.y = y;
        this.rawText = rawText;
    }

    @Override
    public void execute() {

        var playText = rawText.substring(0, textIndexAt);

        TextMgr.getInstance().add(text, playText, new TextPosition(x, y));
        textIndexAt += 1;

        if(textIndexAt <= rawText.length()) {
            var tc = new TimedCommand(this, GameTime.getInstance().getTimeWithDelay(charDelay));
            TimedCommandMgr.getInstance().add(tc);
        }
    }
}
