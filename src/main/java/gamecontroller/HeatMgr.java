package gamecontroller;

public class HeatMgr {

    private static HeatMgr instance;
    int heatSet = 54;
    int heatActual = 0;
    int heatChange = 0;

    public static HeatMgr getInstance() {
        if (instance == null) {
            instance = new HeatMgr();
        }
        return instance;
    }

    public static boolean CheckHeat() {
        return (getInstance().heatActual == 0);
    }

    public void decreaseHeat() {
        heatChange += 1;
    }

    public void refreshHeat() {
        heatSet -= heatChange;
        if(heatActual > heatSet) {
            heatActual = heatSet;
        }
        heatChange = 0;
    }

    public void update() {
        if(heatActual == 0) {
            heatActual = heatSet;
        }
        else {
            heatActual -= 1;
        }

    }

    public int getSetHeat() {
        return heatSet;
    }

    public void reset() {
        this.heatSet = 54;
        this.heatActual = 0;
        this.heatChange = 0;
    }
}
