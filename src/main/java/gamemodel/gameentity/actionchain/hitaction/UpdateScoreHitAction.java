package gamemodel.gameentity.actionchain.hitaction;

import gamecontroller.GameStats;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;

public class UpdateScoreHitAction extends _GameEntityActionChain {
    private int points = 0;

    public UpdateScoreHitAction(int points) {
        super();
        this.points = points;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var score = GameStats.getInstance().getScore();
        score += points;
        GameStats.getInstance().setScore(score);
    }
}
