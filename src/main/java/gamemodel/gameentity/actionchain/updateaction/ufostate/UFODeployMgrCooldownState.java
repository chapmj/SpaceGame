package gamemodel.gameentity.actionchain.updateaction.ufostate;

public class UFODeployMgrCooldownState extends _UFODeployMgrState {
    final int COOLDOWN_TIME_SET = 500;
    int cooldown = COOLDOWN_TIME_SET;

    @Override
    public _UFODeployMgrState execute() {

        cooldown -= 1;
        if(cooldown > 0) {
            return COOLDOWN;
        }
        else {
            cooldown = COOLDOWN_TIME_SET;
            return nextState(context);
        }
    }
}
