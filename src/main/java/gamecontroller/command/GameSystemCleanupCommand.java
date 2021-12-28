package gamecontroller.command;

import gamecontroller.HeatMgr;
import gamecontroller.collision.CollisionMgr;
import gamecontroller.collision.CollisionResolutionMgr;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombDeployMgr;
import gamemodel.gameentity.actionchain.updateaction.ufostate.UFODeployMgr;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage._Sprite;
import gameview.text.TextMgr;

public class GameSystemCleanupCommand extends _GameEntityCommand {

    public void execute() {
        //Gameplay
        HeatMgr.getInstance().reset();
        AlienGridMarchMgr.getInstance().reset();
        GameEntityMgr.getInstance().reset();
        BombDeployMgr.getInstance().reset();
        UFODeployMgr.getInstance().resetService();
        CollisionMgr.getInstance().reset();
        CollisionResolutionMgr.getInstance().reset();

        var sprites = SpriteMgr.getInstance().getAll();
        for (_Sprite sprite : sprites) {
            sprite.clearSubscriptions();
        }

        SpriteMgr.getInstance().reset();

        TextMgr.getInstance().clear();

    }

}
