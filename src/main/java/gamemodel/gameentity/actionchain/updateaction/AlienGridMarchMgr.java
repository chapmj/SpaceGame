package gamemodel.gameentity.actionchain.updateaction;

import gamecontroller.HeatMgr;
import gamecontroller.command.*;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.updateaction.movestate.MoveStateHolder;
import gamemodel.gameentity.actionchain.updateaction.movestate._MoveState;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;


public class AlienGridMarchMgr {
    private static AlienGridMarchMgr instance;
    private ArrayList<ArrayList<IGameEntity>> alienRowsList;
    private MoveStateHolder moveState = new MoveStateHolder();
    private boolean isInsideWall = false;
    private int justMovedCounter;

    Stack<_GameEntityCommand> commandStack = new Stack<>();
    private int pauseTimer = 0;


    public static AlienGridMarchMgr getInstance() {
        if (instance == null) {
            instance = new AlienGridMarchMgr();
            instance.init();
        }

        return instance;
    }

    private void init() {
        instance.alienRowsList = new ArrayList<>(11);
        instance.moveState.setMoveState(_MoveState.MOVE_EAST);
        instance.justMovedCounter = 0;

    }

    public void update() {

        if(pauseTimer == 0) {
            var isHot = HeatMgr.CheckHeat();

            if (alienRowsList != null && isHot && commandStack.isEmpty()) {


                if (isInsideWall) {
                    boolean PUSH_DOWN_STAGE = justMovedCounter == 0;
                    boolean LEAVE_WALL_STAGE = justMovedCounter == 1;
                    boolean SLIDE_HORIZONTAL_STAGE = justMovedCounter == 2;

                    if (PUSH_DOWN_STAGE) {
                        nextMoveState();
                        justMovedCounter = 2;
                    }
                    else if (SLIDE_HORIZONTAL_STAGE) {
                        nextMoveState();
                        justMovedCounter -= 1;
                    }
                    else if (LEAVE_WALL_STAGE) {
                        justMovedCounter -= 1;
                        isInsideWall = false;
                    }
                }

                addMovementToStack();

            }
            if (!commandStack.isEmpty()) {
                var cmd = commandStack.pop();
                if (cmd != null) {
                    cmd.execute();
                }
            }
        }
        else {
            pauseTimer -= 1;
        }

    }

    public void pause(int time) {
        this.pauseTimer = time;
    }

    private void addMovementToStack() {
        //After aliens finish marching, _update heat to change marching speed.

        for(int row = 0; row < alienRowsList.size(); row++) { //once per row
            _GameEntityCommand baseCommand = null;

            if (alienRowsList.get(row) != null) {
                for(int i = 0; i < alienRowsList.get(row).size(); i++) {
                    var alienEntity = alienRowsList.get(row).get(i);

                    var movementCmd = createMovementCommand(alienEntity);
                    baseCommand = addonCommand(baseCommand, movementCmd);
                    baseCommand = addonCommand(baseCommand, new AnimateSpriteCommand(alienEntity));

                }

                var heat = HeatMgr.getInstance().getSetHeat();

                if(row == 0 && heat > 0) {
                    baseCommand = addonCommand(baseCommand, new RefreshHeatCommand());
                }

                if(heat > 45) {
                    pause(4);
                }
                else if (heat > 30) {
                    pause(3);
                }
                else if (heat > 15) {
                    pause(2);
                }
                else if (heat > 5) {
                    pause(1);
                }


                commandStack.add(baseCommand);
            }
        }
    }

    private _GameEntityCommand addonCommand(_GameEntityCommand headCommand, _GameEntityCommand nextCommand) {
        if(headCommand == null) {
            headCommand = nextCommand;
        }
        else {
            headCommand = headCommand.setNext(nextCommand);
        }
        return headCommand;
    }

    private _GameEntityCommand createMovementCommand(IGameEntity alienEntity) {
        Objects.requireNonNull(alienEntity);
        var moveCmd = new MoveCommand(alienEntity); //do per entity
        moveCmd.setMoveState(instance.moveState);
        return moveCmd;
    }

    public void setInsideWall() {
        isInsideWall = true;
    }

    private void nextMoveState() {
        AlienMoveStatePattern.NextState(moveState);
    }

    public void add(ArrayList<IGameEntity> alienRow) {
        this.alienRowsList.add(alienRow);
    }

    public void remove(IGameEntity gameEntity) {
        for(ArrayList<IGameEntity> alienRow : alienRowsList) {
            var isRemoved = alienRow.remove(gameEntity);
            if(isRemoved) {
                if(alienRow.isEmpty()) {
                    alienRowsList.remove(alienRow);
                }
                break;
            }

        }
    }

    public void reset() {
        alienRowsList.clear();
        moveState.setMoveState(_MoveState.MOVE_EAST);
        commandStack.clear();
        isInsideWall = false;
        justMovedCounter = 0;

    }
}
