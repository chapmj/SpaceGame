package gamemodel.gameentity.actionchain.updateaction.bombshootstate;

import gamemodel.gameentity.IGameEntity;

public abstract class _BombShootState {
    public static _BombShootState TRANSIT = new BombShootTransitState();
    public static _BombShootState FIRING = new BombShootFiringState();
    public static _BombShootState STOPPED = new BombShootStoppedState();

    public abstract void update(IGameEntity gameEntity);

    public _BombShootState nextState() {
        if(this instanceof BombShootTransitState) {
            return STOPPED;
        }
        else if(this instanceof BombShootFiringState) {
            return TRANSIT;
        }
        else if(this instanceof BombShootStoppedState) {
            return FIRING;
        }
        return null;
    }
    public abstract void dump();

}
