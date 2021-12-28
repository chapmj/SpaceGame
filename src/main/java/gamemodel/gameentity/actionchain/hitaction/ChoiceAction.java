package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;

import java.util.EnumMap;
import java.util.Objects;

public class ChoiceAction extends _GameEntityActionChain {

    EnumMap<GameEntityMgr.RootNames, _GameEntityActionChain> actions = new EnumMap<>(GameEntityMgr.RootNames.class);
    IGameEntity gameEntity;

    public ChoiceAction(IGameEntity gameEntity) {
        Objects.requireNonNull(gameEntity);
        this.gameEntity = gameEntity;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var collidedName = gameEntityCollidedWith.getName();
        var action = actions.getOrDefault(collidedName, new NullHitAction());
        action.doAction(gameEntity);

    }

    public void add(GameEntityMgr.RootNames rootName, _GameEntityActionChain action) {
        var root = actions.getOrDefault(rootName, new BaseHitAction());
        root.addAction(action);
        actions.put(rootName, root);
    }

}
