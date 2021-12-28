package gamemodel.gamerect;

import java.awt.*;
import java.awt.geom.Rectangle2D.Double;

@SuppressWarnings("unused")
public class WRect implements IRect {
    private static final Rectangle proxyRect = new Rectangle(0,0,0,0);
    private final Double doubleRect;

    public WRect() {
        doubleRect = new Double(0,0,0,0);
    }

    public WRect(double x, double y, double w, double h) {
        doubleRect = new Double(x,y,w,h);
    }

    @Override
    public double x() {
        return doubleRect.x;
    }

    @Override
    public double y() {
        return doubleRect.y;
    }

    @Override
    public double w() {
        return doubleRect.width;
    }

    @Override
    public double h() {
        return doubleRect.height;
    }

    @Override
    public void translate(double x, double y) {
        doubleRect.x += x;
        doubleRect.y += y;
    }

    @Override
    public void positionRect(double x, double y) {
        doubleRect.x = x;
        doubleRect.y = y;

    }

    @Override
    public void setWidth(double w) {
        this.doubleRect.width = w;

    }

    @Override
    public void setHeight(double h) {
        this.doubleRect.height = h;
    }

    @Override
    public boolean intersects(IRect rect) {

        proxyRect.x = (int) rect.x();
        proxyRect.y = (int) rect.y();
        proxyRect.width = (int) rect.w();
        proxyRect.height = (int) rect.h();

        var isIntersecting = doubleRect.intersects(proxyRect);

        return isIntersecting;
    }

    @Override
    public void dump() {
        System.out.printf("Rect: (%f, %f, %f, %f)\n", doubleRect.x, doubleRect.y, doubleRect.width, doubleRect.height);
    }
}
