package gamecontroller.gamesystem;

import gamecontroller.GameStats;
import gamecontroller.HeatMgr;
import gamecontroller.collision.CollisionMgr;
import gamecontroller.collision.CollisionResolutionMgr;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamemodel.gameentity.actionchain.updateaction.AlienGridMarchMgr;
import gamemodel.gameentity.actionchain.updateaction.bombshootstate.BombDeployMgr;
import gamemodel.gameentity.actionchain.updateaction.ufostate.UFODeployMgr;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.sound.Drumbeat;
import gameview.text.TextMgr;
import gameview.text.TextPosition;

public class GameSystemRunningState extends _GameSystemState {
    @Override
    public void _update() {


        //Gameplay
        HeatMgr.getInstance().update();
        AlienGridMarchMgr.getInstance().update();
        GameEntityMgr.getInstance().update();
        BombDeployMgr.getInstance().update();
        UFODeployMgr.getInstance().update();
        CollisionMgr.getInstance().update();
        CollisionResolutionMgr.getInstance().update();
        Drumbeat.getInstance().update();
        GameStats.getInstance().update();

        var gameStats = GameStats.getInstance();
        var score = gameStats.getScore();
        var hiscore = gameStats.getHiscore();
        var lives = gameStats.getLives();

        var livesDisplayed = Math.max(lives, 0);

            TextMgr.getInstance().add(TextMgr.GameText.SCORE, String.format("SCORE %04d       HI-SCORE %04d", score, hiscore), new TextPosition(40, 10));
            TextMgr.getInstance().add(TextMgr.GameText.LIVES, String.format("LIVES %d     <-A D-> SPACE FIRE", livesDisplayed), new TextPosition(40, 690));


        }

    @Override
    protected void handle() {

        if(GameStats.getInstance().getAlienLives() <= 0) {
            var stateChangeCommand = new GameUpdateStateChangeCommand(_GameSystemState.NEXT_ROUND_STATE);
            stateChangeCommand.execute();
        }
        else {
            //stay in running state
        }
    }

    public String toString() {

        return "RUNNING STATE";
    }

}
