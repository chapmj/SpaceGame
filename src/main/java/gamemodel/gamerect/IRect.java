package gamemodel.gamerect;

public interface IRect {
    double x();
    double y();
    double w();
    double h();
    void translate(double x, double y);
    void positionRect(double x, double y);
    void setWidth(double w);
    void setHeight(double h);
    boolean intersects(IRect rect);
    void dump();
}
