package cli.User;

import cli.App;
import cli.Menu;
import instance.TuroSingleton;
import vdm.Turo;

public class UserMenu extends Menu {

    public UserMenu() {
        loop();
    }

    @Override
    protected void initialize() {
        System.out.println("******************************************************");
        System.out.println("*  User Menu                                         *");
        System.out.println("******************************************************");

        addOption("List Cars", () -> new ListCarsUserMenu());
        addOption("See Reservation", () -> new ListUserReservation());
    }
}
