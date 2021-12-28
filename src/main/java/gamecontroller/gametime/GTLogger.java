package gamecontroller.gametime;

import static java.lang.System.out;

public class GTLogger {

    public static void log(String msg) {
        var time = GameTime.getInstance().now();
        out.printf("@%d:%s\n", time, msg);
    }
}
