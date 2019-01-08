package cli.Owner;

import cli.App;
import cli.Menu;
import instance.TuroSingleton;
import vdm.CarOwner;
import vdm.Turo;
import vdm.User;

public class OwnerMenu extends Menu {

    public OwnerMenu() {
        loop();
    }

    @Override
    protected void initialize() {
        Turo turo = TuroSingleton.getTuroInstance();

        CarOwner user = (CarOwner) turo.getUser(App.getEmail());

        System.out.println("******************************************************");
        System.out.println("*  Owner Menu                                        *");
        System.out.println("******************************************************");

        addOption("See Info", () -> seeInfo(user));
        addOption("List Cars", () -> new ListCarsOwnerMenu());
    }

    private void seeInfo(CarOwner user) {
        System.out.println("You have:");
        System.out.println("--------------------");
        System.out.println("-> " + user.getCars().size() + " cars registered");
        System.out.println("-> " + user.getRating() + " Stars rating");
        System.out.println("-> " + user.getNumberTrips() + " total trips");
    }
}
