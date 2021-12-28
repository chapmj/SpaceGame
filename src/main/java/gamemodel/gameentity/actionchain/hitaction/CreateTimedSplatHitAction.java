package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.TimedCommandMgr;
import gamecontroller.command.AnimateSpriteOnlyCommand;
import gamecontroller.command.CreateSplatCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.gameimage.SpriteName;

public class CreateTimedSplatHitAction extends _GameEntityActionChain {
    private final int frameDuration;
    private final int cycles;
    int duration;
    SpriteName spriteName;
    IGameEntity splattedEntity;

    public CreateTimedSplatHitAction(IGameEntity splattedEntity, SpriteName spriteName, int duration) {
        this.splattedEntity = splattedEntity;
        this.spriteName = spriteName;
        this.duration = duration;
        this.frameDuration = 0;
        this.cycles = 0;
    }

    public CreateTimedSplatHitAction(IGameEntity splattedEntity, SpriteName spriteName, int frameDuration, int cycles) {
        this.splattedEntity = splattedEntity;
        this.spriteName = spriteName;
        this.frameDuration = frameDuration;
        this.cycles = cycles;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        createSplat();
    }

    private void createSplat() {
        //create splat
        var w = splattedEntity.getRect().w();
        var h = splattedEntity.getRect().h();
        var x = splattedEntity.getRect().x() + w * 0.5;
        var y = splattedEntity.getRect().y() + h * 0.5;

        var runAtTime = GameTime.getInstance().getTimeNext();

        if(cycles == 0) {
            var createSplatCmd = new CreateSplatCommand(spriteName, x, y, duration);//d=30
            var tc = new TimedCommand(createSplatCmd, runAtTime);
            TimedCommandMgr.getInstance().add(tc);
        }

        if(cycles > 0) {
            var animationDuration = frameDuration * (cycles + 1);
            var createSplatCmd = new CreateSplatCommand(spriteName, x, y, animationDuration);//d=30
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
