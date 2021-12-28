package ui;

import javafx.scene.canvas.Canvas;

import java.util.HashMap;

public class CanvasMgr
{
    private static CanvasMgr instance;
    private final HashMap<CanvasNames, Canvas> canvases;

    private CanvasMgr(HashMap<CanvasNames, Canvas> canvases) {
        this.canvases = canvases;
    }

    public static CanvasMgr getInstance() {
        if (instance == null) {
            instance = new CanvasMgr(new HashMap<>(4));
            instance.init();
        }
        return instance;
    }

    private void init() {
        instance.add(CanvasNames.LAYER_ENEMIES, new Canvas(0, 0));
        instance.add(CanvasNames.LAYER_GLYPHS, new Canvas(0, 0));
        instance.add(CanvasNames.LAYER_DEBUG, new Canvas(0, 0));
    }

    public void add(CanvasNames canvasName, Canvas canvas) {
        this.canvases.put(canvasName, canvas);
    }

    public Canvas get(CanvasNames canvasName) {
        var canvas = canvases.get(canvasName);
        return canvas;
    }

    public enum CanvasNames {
        LAYER_ENEMIES, LAYER_GLYPHS, LAYER_DEBUG
    }
}
