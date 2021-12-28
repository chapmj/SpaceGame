package gameview.gameimage;

import gamemodel.gameentity.ISpriteSubscriber;

import java.util.ArrayList;
import java.util.Stack;

public abstract class _Sprite {
    private final ArrayList<ISpriteSubscriber> subscribers;
    private final Stack<ISpriteSubscriber> stack;

    public _Sprite() {
        this.subscribers = new ArrayList<>();
        this.stack = new Stack<>();
    }
    public abstract _GameImage getImage();

    public abstract double getX();

    public abstract double getY();

    public abstract double getW();

    public abstract double getH();

    public abstract void setX(double x);

    public abstract void setY(double y);

    public void attach(ISpriteSubscriber spriteSubscriber) {
        subscribers.add(spriteSubscriber);

    }

    public void detach(ISpriteSubscriber spriteSubscriber) {
        subscribers.remove(spriteSubscriber);
    }

    public void initialize() {
        stack.addAll(subscribers);
    }

    public void reset() {
        stack.clear();
    }

    public void clearSubscriptions() {
        this.subscribers.clear();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }


    public void updateNext() {
        var spriteSubscriber = stack.pop();
        var rect = spriteSubscriber.getRect();//gets the game entity-rect sprite is attached to
        setX(rect.x());
        setY(rect.y());
    }

    public void nextFrame() { }

    public abstract SpriteName getName();
}
