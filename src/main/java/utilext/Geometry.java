package utilext;

import gamemodel.gamerect.IRect;
import gamemodel.gamerect.WRect;

import java.util.List;

public final class Geometry {


    public static double[] getCenter(double x, double y, double w, double h) {

        double cX = x + w * 0.5;
        double cY = y + h * 0.5;

        return new double[]{cX, cY};
    }

    public static double[] getCorner(double cX,double  cY, double w, double  h) {

        double kX = cX - (w * 0.5);
        double kY = cY - (h * 0.5);

        return new double[]{kX, kY};
    }

    public static int calcMidpoint(int low, int high) {
        return low + high >>> 1; //Midpoint
    }

    public static IRect mergeRects(List<IRect> rects) {
        // Merge all rects
        double x = -1;
        double y = -1;
        double w = -1;
        double h = -1;

        for(IRect rect : rects) {

            var xLeft = 0.0;
            if(x == -1) {
                xLeft = rect.x();
            }
            else xLeft = Math.min(x, rect.x());

            var yTop = 0.0;
            if(y == -1) {
                yTop = rect.y();
            }
            else {
                yTop = Math.min(y, rect.y());
            }

            var xRight = 0.0;
            if(w == -1) {
                xRight = rect.x() + rect.w();
            }
            else xRight = Math.max(x + w, rect.x() + rect.w());

            var yBottom = 0.0;
            if(h == -1) {
                yBottom = rect.y() + rect.h();
            }
            else yBottom = Math.max(y + h, rect.y() + rect.h());

            w = xRight - xLeft;
            h = yBottom - yTop;
            x = xLeft;
            y = yTop;

        }

        if(rects.isEmpty()) {
            x = 0;
            y = 0;
            w = 0;
            h = 0;
        }

        return new WRect(x, y, w, h);









    }
}
