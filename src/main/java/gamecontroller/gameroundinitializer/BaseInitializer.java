package gamecontroller.gameroundinitializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseInitializer extends _Initializer {

    List<_Initializer> initializers;

    public BaseInitializer(_Initializer... initializers) {
        this.initializers = new ArrayList<>(Arrays.asList(initializers));
    }

    public void init() {
        for(_Initializer initializer : initializers) {
            initializer.init();
        }
    }

    public void add(_Initializer initializer) {
        this.initializers.add(initializer);
    }
}
