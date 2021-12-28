package gamecontroller.gamesystem;

import gamecontroller.TimedCommandMgr;
import gamecontroller.command.AddToSpriteMgrCommand;
import gamecontroller.command.GameUpdateStateChangeCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameSystemNewRoundAnimationState extends _GameSystemState {

    private int delay = 0;
    @Override
    public void _update() {

        var root = GameEntityMgr.getInstance().get(GameEntityMgr.RootNames.ALIEN_ROOT).getGameEntityComponent();

        var cols = root.getChildren().get(0).getChildren();
        var numRows = cols.get(0).getChildren().size();
        List<List<_GameEntityComponent>> rows = new ArrayList<>(numRows);

        for (int i = 0; i < numRows; i++) {
            rows.add(new ArrayList<>(cols.size()));
        }

        for (int i = cols.size() - 1; i >= 0; i--) {

            var col = cols.get(i).getChildren();

            for (int j = col.size() - 1; j >= 0; j--) {
                var row = rows.get(j);
                row.add(col.get(j));
            }
        }

        delay = numRows * cols.size();


        var flatrows = rows.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        for (int i = 0; i < flatrows.size(); i++) {

            var runAtTime = GameTime.getInstance().getTimeWithDelay((char) (delay + 60));
            var spritemgr = new AddToSpriteMgrCommand(flatrows.get(i).getSprite());
            var tc = new TimedCommand(spritemgr, runAtTime);
            TimedCommandMgr.getInstance().add(tc);
            delay -= 1;
        }

    }


    @Override
    protected void handle() {
        var stoppedGameState = new GameUpdateStateChangeCommand(_GameSystemState.STOPPED_STATE);
        stoppedGameState.execute();

        var nextGameState = new GameUpdateStateChangeCommand(_GameSystemState.RUNNING_STATE);
        var runAtTime = GameTime.getInstance().getTimeWithDelay((char) (delay + 120));
        var nextGameStateTc = new TimedCommand(nextGameState, runAtTime);
        TimedCommandMgr.getInstance().add(nextGameStateTc);

    }

    public String toString() {
        return "NEW_ROUND_ANIMATION STATE";
    }
}
