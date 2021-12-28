package gamemodel.gamerect;

public class FactoryRectangle {

    private static IRect nullRect = new NullRect();


    public static IRect create(double w, double h) {

        return new WRect(0, 0, w, h);
    }

    public static IRect getNull() {
        return nullRect;
    }

    public static IRect create() {
        return new WRect(0, 0, 0, 0);
    }
}
