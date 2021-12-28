package gamemodel.gamerect;

public class NullRect implements IRect
{
    @Override
    public double x()
    {
        return 0;
    }

    @Override
    public double y()
    {
        return 0;
    }

    @Override
    public double w()
    {
        return 0;
    }

    @Override
    public double h()
    {
        return 0;
    }

    @Override
    public void translate(double x, double y) { }

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
        System.out.println("NullRect(0,0,0,0)");
    }
}
