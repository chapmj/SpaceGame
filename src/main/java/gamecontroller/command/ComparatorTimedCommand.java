package gamecontroller.command;

import java.util.Comparator;

public class ComparatorTimedCommand implements Comparator<TimedCommand> {
    @Override
    public int compare(TimedCommand o1, TimedCommand o2) {
        return Integer.compare(o1.getRunAtTime(), o2.getRunAtTime());
    }
}
