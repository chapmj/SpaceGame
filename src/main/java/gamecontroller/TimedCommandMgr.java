package gamecontroller;

import gamecontroller.command.ComparatorTimedCommand;
import gamecontroller.command.TimedCommand;
import gamecontroller.gametime.GameTime;

import java.util.PriorityQueue;

public class TimedCommandMgr {
    static TimedCommandMgr instance;
    PriorityQueue<TimedCommand> commands;

    TimedCommandMgr() {
        var comparator = new ComparatorTimedCommand();
        this.commands = new PriorityQueue<>(comparator);
    }

    public static TimedCommandMgr getInstance() {
        if(instance == null) {
            instance = new TimedCommandMgr();
        }
        return instance;
    }

    public void add(TimedCommand c) {
        commands.add(c);
    }

    public void update() {
        var currentTime = GameTime.getInstance().now();

        while(!commands.isEmpty() && commands.peek().getRunAtTime() == currentTime) {
            var timedCommand = commands.poll();

            if(timedCommand != null) {
                timedCommand.execute();
            }

        }

    }

    public void clear() {
        commands.clear();
    }
}
