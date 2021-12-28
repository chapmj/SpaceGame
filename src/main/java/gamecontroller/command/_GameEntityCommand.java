package gamecontroller.command;

public abstract class _GameEntityCommand implements _ICommand {

    private _GameEntityCommand next = null;

    public _GameEntityCommand() { }

    //public abstract void execute(IGameEntity gameEntity);

    protected void executeNext() {
        if(next != null) {
            next.execute();
        }
    }

    public void execute() {
        System.out.println("running default _GameEntityCommand do nothing");
    }

    public _GameEntityCommand setNext(_GameEntityCommand c) {
        c.next = this;
        return c;
    }
}
