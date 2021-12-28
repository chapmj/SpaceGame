package gamecontroller.command;


public class TimedCommand implements _ICommand {
    int runAtTime = 0;
    _ICommand command;

    public TimedCommand(_ICommand command, int runAtTime) {
        this.runAtTime = runAtTime;
        this.command = command;
    }

    public int getRunAtTime() {
        return runAtTime;
    }

// --Commented out by Inspection START (2021-12-27 23:42):
//    public _ICommand getCommand() {
//        return this.command;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:42)

// --Commented out by Inspection START (2021-12-27 23:42):
//    public void setCommand(_ICommand command) {
//        this.command = command;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:42)

    public void execute() {
        if(this.command != null) {
            this.command.execute();
        }
    }
}
