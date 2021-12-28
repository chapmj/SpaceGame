package ui;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;

public class StageMgr {

    private static StageMgr instance;
    private final HashMap<StageMgr.Stages, Stage> stages;

    private StageMgr(HashMap<StageMgr.Stages, Stage> stages) {
        this.stages = stages;
    }

    public static StageMgr getInstance() {
        if (instance == null) {
            instance = new StageMgr(new HashMap<>(4));
        }
        return instance;
    }

    public void init(Stage gameStage) {
        configureGameStage(gameStage);
        instance.add(Stages.STAGE_GAME, gameStage);
    }

    public void add(StageMgr.Stages stageName, Stage stage) {
        this.stages.put(stageName, stage);
    }

    public Stage get(StageMgr.Stages stageName) {
        var stage = stages.get(stageName);
        return stage;
    }

    private void configureGameStage(Stage stage) {
        var scene = SceneMgr.getInstance().get(SceneMgr.Scenes.Scene_Game);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setTitle("Space Game");
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    }

    public enum Stages {
        STAGE_GAME
    }

}
