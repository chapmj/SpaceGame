package gamecontroller.command;

import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

public class AnimateSpriteOnlyCommand extends _GameEntityCommand {

    private _Sprite sprite;

    public AnimateSpriteOnlyCommand(_Sprite sprite) {
        if (sprite == null) {
            var defaultSprite = SpriteMgr.getInstance().get(SpriteName.DEFAULT);
            this.sprite = defaultSprite;
        } else {
            this.sprite = sprite;
        }
    }

    public void execute() {
        sprite.nextFrame();
        executeNext();
    }
}
