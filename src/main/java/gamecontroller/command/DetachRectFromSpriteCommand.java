package gamecontroller.command;

import gameview.gameimage.SpriteMgr;
import gameview.gameimage._Sprite;

public class DetachRectFromSpriteCommand implements _ICommand {
    _Sprite sprite;

    public DetachRectFromSpriteCommand(_Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void execute() {
        SpriteMgr.getInstance().remove(sprite);
    }
}
