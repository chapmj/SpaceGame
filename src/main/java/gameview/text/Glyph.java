package gameview.text;

import gamemodel.gamerect.IRect;
import gamemodel.gamerect.WRect;
import gameview.gameimage.Image;

public class Glyph implements IRect {
    Image image;
    WRect displayRect;

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDisplayRect(WRect displayRect) {
        this.displayRect = displayRect;
    }

    @Override
    public double x() {
        return 0;
    }

    @Override
    public double y() {
        return 0;
    }

    @Override
    public double w() {
        return 0;
    }

    @Override
    public double h() {
        return 0;
    }

    @Override
    public void translate(double x, double y) {

    }

    @Override
    public void positionRect(double x, double y) {

    }

    @Override
    public void setWidth(double w) {

    }

    @Override
    public void setHeight(double h) {

    }

    @Override
    public boolean intersects(IRect rect) {
        return false;
    }

    @Override
    public void dump() {

    }
}
