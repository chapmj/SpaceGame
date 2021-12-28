package gamemodel.gameentity.factory;

import gamecontroller.command.ChangeHitAction;
import gamecontroller.command.CreatePermaSplatCenteredCmd;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.DestroyShieldCellDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.ChoiceAction;
import gamemodel.gameentity.actionchain.hitaction.CommandExecutorAction;
import gamemodel.gameentity.actionchain.hitaction.DestroyGameEntityHitAction;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteName;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.*;

public class FactoryShieldCell extends _FactoryGameEntity {

    @Override
    public IGameEntity create() {
        var gameEntity = FactoryGameEntity.Create(0, 0, 0, GameEntityMgr.RootNames.SHIELD_ROOT);

        var crateredHitAction = new ChoiceAction(gameEntity);
        crateredHitAction.add(ALIEN_ROOT, new CommandExecutorAction(new CreatePermaSplatCenteredCmd(gameEntity, SpriteName.SPLAT, 0, 0)));
        crateredHitAction.add(ALIEN_ROOT, new DestroyGameEntityHitAction(gameEntity));

        //Standard hitAction
        var hitAction = new ChoiceAction(gameEntity);
        hitAction.add(BOMB_ROOT, new CommandExecutorAction(new CreatePermaSplatCenteredCmd(gameEntity, SpriteName.SPLAT, 0, 0)));
        hitAction.add(BOMB_ROOT, new CommandExecutorAction(new ChangeHitAction(gameEntity, crateredHitAction)));
        hitAction.add(BOMB_ROOT, new DestroyGameEntityHitAction(gameEntity));

        hitAction.add(MISSILE_ROOT, new DestroyGameEntityHitAction(gameEntity));
        hitAction.add(MISSILE_ROOT, new CommandExecutorAction(new CreatePermaSplatCenteredCmd(gameEntity, SpriteName.SPLAT, 0, 0)));
        hitAction.add(MISSILE_ROOT, new CommandExecutorAction(new ChangeHitAction(gameEntity, crateredHitAction)));

        hitAction.add(ALIEN_ROOT, new CommandExecutorAction(new CreatePermaSplatCenteredCmd(gameEntity, SpriteName.SPLAT, 0, 0)));
        hitAction.add(ALIEN_ROOT, new DestroyGameEntityHitAction(gameEntity));

        gameEntity.setHitAction(hitAction);

        var destroyAction = new DestroyShieldCellDestroyAction();
        gameEntity.setDestroyAction(destroyAction);

        return gameEntity;
    }

}
