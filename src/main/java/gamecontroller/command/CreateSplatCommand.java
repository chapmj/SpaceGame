package gamecontroller.command;

import gamecontroller.TimedCommandMgr;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.RectSpriteSubscriber;
import gamemodel.gamerect.WRect;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

public class CreateSplatCommand implements _ICommand {
    private _Sprite splatSprite;
    private double x;
    private double y;
    private final int duration;

    public CreateSplatCommand(SpriteName splatName, double x, double y, int duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.splatSprite = SpriteMgr.getInstance().create(splatName);
    }

@Override
    public void execute() {
        SpriteMgr.getInstance().add(this.splatSprite);
        //align splat
        x = x - splatSprite.getW() * 0.5;
        y = y - splatSprite.getH() * 0.5;
        splatSprite.setX(x);
        splatSprite.setY(y);

        var spriteSubscriber = new RectSpriteSubscriber(new WRect(x, y, splatSprite.getW(), splatSprite.getH()));

        splatSprite.attach(spriteSubscriber);

        //var detachSplatCmd = new DetachRectFromSpriteCommand(splatName, spriteSubscriber);
        var detachSplatCmd = new DetachRectFromSpriteCommand(splatSprite);

        var runAtTime = GameTime.getInstance().getTimeWithDelay((char)duration);
        var tc = new TimedCommand(detachSplatCmd, runAtTime);
        TimedCommandMgr.getInstance().add(tc);

    }

    public _Sprite getSprite() {
        return this.splatSprite;
    }

}
