package gamecontroller.gameroundinitializer;

import gamecontroller.command.CreateShipCommand;

public class ShipInitializer extends _Initializer {
    @Override
    public void init() {
        var createShipCmd = new CreateShipCommand();
        createShipCmd.execute();
    }
}
