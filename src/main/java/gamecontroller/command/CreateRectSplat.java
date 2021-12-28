package gamecontroller.command;

import gamemodel.gamerect.IRect;
import gameview.gameimage.RectMgr;

public class CreateRectSplat implements _ICommand {

    private final IRect rect;

    public CreateRectSplat(IRect rect) {
        this.rect = rect;
    }

    @Override
    public void execute() {
        RectMgr.getInstance().add(rect);


    }
}
