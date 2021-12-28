package ui;

import gamecontroller.input.KeyboardController;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

import static ui.SceneMgr.Scenes.Scene_Game;

public class SceneMgr
{
    private static SceneMgr instance;
    private final HashMap<Scenes, Scene> scenes;
    private static final int sceneW = 800;
    private static final int sceneH = 720;

    private SceneMgr(HashMap<Scenes, Scene> scenes) {
        this.scenes = scenes;
    }

    public static SceneMgr getInstance() {
        if (instance == null) {
            instance = new SceneMgr(new HashMap<>(4));
            instance.init();
        }
        return instance;
    }

    private void init() {
        Scene gameScene = configureGameScene();
        gameScene.setOnKeyPressed((event) -> KeyboardController.getInstance().receiveKeyEvent(event));
        gameScene.setOnKeyReleased((event) -> KeyboardController.getInstance().receiveKeyEvent(event));
        //gameScene.setOnKeyTyped((event) -> KeyboardController.getInstance().receiveKeyEvent(event));
        instance.add(Scene_Game, gameScene);
    }

    public void add(Scenes sceneName, Scene scene) {
        this.scenes.put(sceneName, scene);
    }

    public Scene get(Scenes sceneName) {
        var scene= scenes.get(sceneName);
        return scene;
    }

    private Scene configureGameScene() {
        var pane = new StackPane();
        pane.setStyle("-fx-background-color: black;");
        var canvasEnemies = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_ENEMIES);

        canvasEnemies.setWidth(sceneW);
        canvasEnemies.setHeight(sceneH);

        var canvasDebug = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_DEBUG);
        canvasDebug.setWidth(sceneW);
        canvasDebug.setHeight(sceneH);

        var canvasGlyphs = CanvasMgr.getInstance().get(CanvasMgr.CanvasNames.LAYER_GLYPHS);
        canvasGlyphs.setWidth(sceneW);
        canvasGlyphs.setHeight(sceneH);

        pane.getChildren().addAll(canvasEnemies, canvasGlyphs, canvasDebug);
        Scene scene = new Scene(pane, sceneW, sceneH);
        return scene;
    }

    public enum Scenes {
        Scene_Game
    }
}
