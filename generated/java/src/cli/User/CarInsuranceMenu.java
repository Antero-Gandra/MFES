package cli.User;

import cli.App;
import cli.Menu;
import instance.TuroSingleton;
import org.overture.codegen.runtime.VDMSet;
import vdm.*;

public class CarInsuranceMenu extends Menu {

    private String vehiclePlate;
    private Date startDate;
    private Date endDate;

    public CarInsuranceMenu(String vehicle, Date finalStartDate, Date finalEndDate) {
        vehiclePlate = vehicle;
        startDate = finalStartDate;
        endDate = finalEndDate;
        loop();
    }

    @Override
    protected void initialize() {
        Turo turo = TuroSingleton.getTuroInstance();
        VDMSet insurances = turo.getInsurances();

        insurances.forEach(insuranceObj -> {
            Insurance insurance = (Insurance) insuranceObj;
            String option = String.format("%15s | %2s€ / Day | %2s Deposit",
                    insurance.getInsuranceType(),
                    insurance.getPrice(),
                    insurance.getDepositValue());
            addOption(option, () -> addReservation(insurance));
        });
    }

    private void addReservation(Insurance insurance) {
        Turo turo = TuroSingleton.getTuroInstance();

        User user = TuroSingleton.getTuroInstance().getUser(App.getEmail());
        Vehicle vehicle = turo.getVehicle(vehiclePlate);

        Reservation reservation = new Reservation(turo.getInsurance(insurance.getInsuranceType()), startDate, endDate);

        insurance.addReservation(reservation);
        user.addReservation(reservation);
        vehicle.addReservation(reservation);
        new UserMenu();
    }
}
