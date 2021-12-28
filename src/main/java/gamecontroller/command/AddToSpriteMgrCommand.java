package gamecontroller.command;

import gameview.gameimage.SpriteMgr;
import gameview.gameimage._Sprite;

public class AddToSpriteMgrCommand extends _GameEntityCommand {


    private final _Sprite sprite;

    public AddToSpriteMgrCommand(_Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void execute() {
        SpriteMgr.getInstance().add(sprite);
    }

}
