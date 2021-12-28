package ui;

import gamecontroller.gamesystem.GameSystemMgr;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("Starting ui.App");

        StageMgr.getInstance().init(stage);

        var game = GameSystemMgr.getInstance().get(GameSystemMgr.GameSystemName.MAIN_GAME);
        game.start();
    }

    @Override
    public void stop() {
        GameSystemMgr.getInstance().get(GameSystemMgr.GameSystemName.MAIN_GAME).stop();
        System.out.println("Stopping ui.App");

        try {
            super.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}