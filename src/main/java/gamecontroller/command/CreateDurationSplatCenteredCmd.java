package gamecontroller.command;

import gamecontroller.TimedCommandMgr;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.IGameEntity;
import gameview.gameimage.SpriteName;
import utilext.Geometry;

public class CreateDurationSplatCenteredCmd implements _ICommand {
    private final SpriteName splatSpriteName;
    private final int offsetX;
    private final int offsetY;
    private final int cycles;
    private final int frameDuration;
    private final int duration;
    IGameEntity gameEntity;

    public CreateDurationSplatCenteredCmd(IGameEntity gameEntity, SpriteName splatSpriteName, int offsetX, int offsetY, int duration) {
        this.gameEntity = gameEntity;
        this.splatSpriteName = splatSpriteName;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.cycles = 0;
        this.frameDuration = 0;
        this.duration = duration;
    }

    @Override
    public void execute() {
        //create splat
        var w = gameEntity.getRect().w();
        var h = gameEntity.getRect().h();
        var position = Geometry.getCenter(gameEntity.getRect().x(), gameEntity.getRect().y(), w, h);
        var x = position[0] + offsetX;
        var y = position[1] + offsetY;

        var runAtTime = GameTime.getInstance().getTimeNext();

        if(cycles == 0) {
            var createSplatCmd = new CreateSplatCommand(splatSpriteName, x, y, duration);//d=30
            var tc = new TimedCommand(createSplatCmd, runAtTime);
            TimedCommandMgr.getInstance().add(tc);
        }

        if(cycles > 0) {
            var animationDuration = frameDuration * (cycles + 1);
            var createSplatCmd = new CreateSplatCommand(splatSpriteName, x, y, animationDuration);//d=30
            var sprite = createSplatCmd.getSprite();

            var tc1 = new TimedCommand(createSplatCmd, runAtTime);
            TimedCommandMgr.getInstance().add(tc1);

            var animateCommand = new AnimateSpriteOnlyCommand(sprite);

            for(int i = 0; i < cycles; i++) {
                var startFrameAt = frameDuration * (i + 1) + runAtTime;
                var tc2 = new TimedCommand(animateCommand, startFrameAt);
                TimedCommandMgr.getInstance().add(tc2);
            }

        }
    }
}
