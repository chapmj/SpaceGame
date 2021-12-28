package gameview.gameimage;

import gamemodel.gamerect.WRect;

import java.util.ArrayList;

public class ImageSet extends _GameImage
{

    private ArrayList<WRect> skeletons;
    private int skeletonCursor = 0;

    public ImageSet(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.skeletons = new ArrayList<>();
        add(x, y, w, h);
    }

    public void add(double x, double y, double w, double h) {
        var skeleton = new WRect(x, y, w, h);
        this.skeletons.add(skeleton);
    }

    @Override
    public void handle() {
        var maxSkeleton = skeletons.size() - 1;
        if (skeletonCursor < maxSkeleton) {
            skeletonCursor += 1;
        }
        else {
            skeletonCursor = 0;
        }

        x = skeletons.get(skeletonCursor).x();
        y = skeletons.get(skeletonCursor).y();
        w = skeletons.get(skeletonCursor).w();
        h = skeletons.get(skeletonCursor).h();

    }
}
