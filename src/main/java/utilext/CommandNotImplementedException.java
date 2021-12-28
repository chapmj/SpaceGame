package utilext;

public class CommandNotImplementedException extends Exception {

    public CommandNotImplementedException() {
        super("Command not implemented.");
    }
}
