package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.HeatMgr;
import gamecontroller.TimedCommandMgr;
import gamecontroller.collision.CollisionMgr;
import gamecontroller.collision.CollisionResolutionMgr;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombDeployMgr;
import gamemodel.gameentity.actionchain.updateaction.ufostate.UFODeployMgr;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage._Sprite;
import gameview.text.TextMgr;

public class GameSystemCleanupState extends _GameSystemState {
    @Override
    public void _update() {

        //Gameplay
        HeatMgr.getInstance().reset();
        AlienGridMarchMgr.getInstance().reset();
        GameEntityMgr.getInstance().reset();
        BombDeployMgr.getInstance().reset();
        UFODeployMgr.getInstance().resetService();
        CollisionMgr.getInstance().reset();
        CollisionResolutionMgr.getInstance().reset();

        var sprites = SpriteMgr.getInstance().getAll();
        for(_Sprite sprite : sprites) {
            sprite.clearSubscriptions();
        }

        SpriteMgr.getInstance().reset();

        TextMgr.getInstance().clear();

        TimedCommandMgr.getInstance().clear();


    }

    @Override
    public void handle() {
        //Check State
        if(GameStats.getInstance().getLives() <= 0) {
            GameStats.getInstance().reset();

            var gameStateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.TITLE_MENU_STATE);
            var changeStateTimedCommand = new TimedCommand(gameStateChangeCommand, GameTime.getInstance().getTimeWithDelay((char)60));
            TimedCommandMgr.getInstance().add(changeStateTimedCommand);


        }

        else if(GameStats.getInstance().getAlienLives() == 0) {
            var gameStateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.INITIALIZING_STATE);
            var changeStateTimedCommand = new TimedCommand(gameStateChangeCommand, GameTime.getInstance().getTimeWithDelay((char)60));
            TimedCommandMgr.getInstance().add(changeStateTimedCommand);

        }

        else {
            try {
                throw new Exception("Unknown _GameSystemState");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        var stopCommand = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stopCommand.execute();

    }

    public String toString() {
        return "CLEANUP STATE";
    }
}
