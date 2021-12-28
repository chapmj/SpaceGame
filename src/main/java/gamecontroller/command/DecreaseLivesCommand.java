package gamecontroller.command;

import gamecontroller.GameStats;

public class DecreaseLivesCommand extends _GameEntityCommand {

    @Override
    public void execute() {

        var currentLives = GameStats.getInstance().getLives();
        var nextLives = currentLives -= 1;
        GameStats.getInstance().setLives(nextLives);
    }

}
