package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.gameimage._Sprite;

public class UnsubscribeSpriteAction extends _GameEntityActionChain {

    _Sprite gameSprite;
    GameEntitySpriteSubscriber subscriber;

    public UnsubscribeSpriteAction(_Sprite gameSprite, GameEntitySpriteSubscriber subscriber) {
        this.subscriber = subscriber;
        this.gameSprite = gameSprite;
    }
    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        gameSprite.detach(subscriber);
    }
}
