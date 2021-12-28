package gamemodel.gameentity.actionchain.updateaction.movestate;

import gamemodel.gameentity.IGameEntity;

public abstract class _MoveState {
    public static _MoveState MOVE_NORTH = new MoveNorth();
    public static _MoveState MOVE_SOUTH = new MoveSouth();
    public static _MoveState MOVE_SOUTH_FIXED = new MoveSouthFixedSpeed();
    public static _MoveState MOVE_EAST = new MoveEast();
    public static _MoveState MOVE_WEST = new MoveWest();
    public static _MoveState STOPPED = new MoveStopped();
    public static _MoveState MOVE_NULL = new NullMoveState();

    public abstract void update(IGameEntity gameEntity);

    public void dump() {}
}
