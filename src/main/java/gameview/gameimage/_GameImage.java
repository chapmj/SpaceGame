package gameview.gameimage;

public abstract class _GameImage
{
    double x;
    double y;
    double w;
    double h;

    public _GameImage(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double w() {
        return this.w;
    }

    public double h() {
        return this.h;
    }

    public void handle() {}

}
