package cli.User;

import cli.Menu;
import instance.TuroSingleton;
import org.overture.codegen.runtime.VDMSet;
import vdm.CarOwner;
import vdm.Turo;
import vdm.Vehicle;
import vdm.VehicleInfo;

public class ListCarsUserMenu extends Menu {

    public ListCarsUserMenu() {
        loop();
    }

    @Override
    protected void initialize() {
        Turo turo = TuroSingleton.getTuroInstance();
        VDMSet carOwners = turo.getCarOwners();
        VDMSet cars = new VDMSet();

        /* Populate Cars */
        carOwners.forEach((carOwnerObj -> {
            CarOwner carOwner = (CarOwner) carOwnerObj;
            cars.addAll(carOwner.getCars());
        }));

        cars.forEach(carObj -> {
            Vehicle vehicle = (Vehicle) carObj;
            VehicleInfo vehicleInfo = vehicle.getVehicleInfo();
            String option = String.format("%10s | %2s€ / Day | %2s seats | %10s | %5s | %1s Stars",
                    vehicleInfo.getBrand(),
                    vehicle.getDailyPrice().intValue(),
                    vehicleInfo.getSeats().intValue(),
                    vehicleInfo.getFuelType(),
                    vehicleInfo.getYear().intValue(),
                    vehicle.getRating().intValue());
            addOption(option, () -> new CarInformationMenu(vehicle, vehicleInfo));
        });
    }
}
