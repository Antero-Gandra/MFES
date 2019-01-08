package cli.Owner;

import cli.App;
import cli.Menu;
import instance.TuroSingleton;
import org.overture.codegen.runtime.VDMSet;
import vdm.CarOwner;
import vdm.Turo;
import vdm.Vehicle;
import vdm.VehicleInfo;

public class ListCarsOwnerMenu extends Menu {

    public ListCarsOwnerMenu() {
        loop();
    }

    @Override
    protected void initialize() {
        Turo turo = TuroSingleton.getTuroInstance();

        CarOwner user = (CarOwner) turo.getUser(App.getEmail());
        VDMSet cars = user.getCars();

        cars.forEach(carObj -> {
            Vehicle vehicle = (Vehicle) carObj;
            VehicleInfo vehicleInfo = vehicle.getVehicleInfo();
            String option = String.format("%10s | %2s Current Reservations | %1s Stars",
                    vehicleInfo.getBrand(),
                    vehicle.getReservations().size(),
                    vehicle.getRating().intValue());
            addOption(option, () -> System.out.println(vehicle + "\n" + vehicleInfo));
        });
    }
}
