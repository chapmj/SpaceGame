package gamemodel.gameentity.actionchain.updateaction.ufostate;

import java.util.Random;

public class UFODeployMgrSeedingState extends _UFODeployMgrState {

    Random random = new Random();
    final int SEED_WAIT = 500;

    @Override
    public _UFODeployMgrState execute() {
        var seed = Math.abs(random.nextInt());
        if(seed % SEED_WAIT == 0) {
            return nextState(context);
        }
        return _UFODeployMgrState.SEEDING;
    }
}
