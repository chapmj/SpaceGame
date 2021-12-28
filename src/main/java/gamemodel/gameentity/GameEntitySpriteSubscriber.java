package gamemodel.gameentity;

import gamemodel.gamerect.IRect;

public class GameEntitySpriteSubscriber implements ISpriteSubscriber {

    private final IGameEntity gameEntity;

    public GameEntitySpriteSubscriber(IGameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }

    @Override
    public IRect getRect() {
        return this.gameEntity.getRect();
    }
}
