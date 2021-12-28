package gamemodel.gameentity.gameentitycomponent;

import java.util.Iterator;
import java.util.Stack;

public class GameEntityComponentIterator implements Iterator<_GameEntityComponent> {
    Stack<_GameEntityComponent> stack;
    int childCntr = 0;

    public GameEntityComponentIterator() {
        this.stack = new Stack<>();
    }

    public GameEntityComponentIterator(_GameEntityComponent gameEntityComponent) {
        this.stack = new Stack<>();
        this.stack.push(gameEntityComponent);

    }

    public void add(_GameEntityComponent gameEntityComponent) {
        stack.push(gameEntityComponent);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public _GameEntityComponent next() {
        var gameEntityComponent = stack.pop();
        for(_GameEntityComponent child : gameEntityComponent.getChildren()) {
            stack.push(child);
            childCntr += 1;
        }
        return gameEntityComponent;
    }

    public void branch() {
        for(int i = 0; i < childCntr; i++) {
            stack.pop();
            childCntr -= 1;
        }
    }

    public _GameEntityComponent peek() {
        return stack.peek();
    }

}
