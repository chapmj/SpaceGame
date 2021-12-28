package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.GameStats;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class UpdateUFOScoreHitAction extends _GameEntityActionChain {

    public UpdateUFOScoreHitAction() {
        super();
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {

        //Mystery points
        var points = 50;
        var numShots = GameStats.getInstance().getShotsFired();
        if(numShots % 10 == 0) {
            points = 300;
        }

        var score = GameStats.getInstance().getScore();
        score += points;
        GameStats.getInstance().setScore(score);
    }

}
