package gamecontroller.command;

import gamemodel.gameentity.IGameEntity;

public class AnimateSpriteCommand extends _GameEntityCommand {

    private IGameEntity gameEntity;

    public AnimateSpriteCommand(IGameEntity gameEntity) {

        this.gameEntity = gameEntity;
    }

    public void execute() {
        var sprite = gameEntity.getSprite();
        sprite.nextFrame();

        executeNext();

    }
}
