package gamecontroller.command;

import gamecontroller.gametime.GameTime;
import gameview.gameimage._Sprite;

public class AnimateIntervalSpriteCommand extends _GameEntityCommand {

    private _Sprite sprite;
    private int interval;
    private GameTime timeSvc;

    public AnimateIntervalSpriteCommand(_Sprite sprite, int interval, GameTime timeSvc) {

        this.sprite = sprite;
        this.interval = interval;
        this.timeSvc = timeSvc;
    }

    public void execute() {
        if(timeSvc.now() % interval == 0) {
            sprite.nextFrame();
        }

        executeNext();

    }
}
