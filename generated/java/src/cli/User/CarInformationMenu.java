package cli.User;

import cli.Menu;
import instance.TuroSingleton;
import vdm.Date;
import vdm.Turo;
import vdm.Vehicle;
import vdm.VehicleInfo;

public class CarInformationMenu extends Menu {

    private Vehicle vehicle;
    private VehicleInfo vehicleInfo;

    public CarInformationMenu(Vehicle vehicle, VehicleInfo vehicleInfo) {
        this.vehicle = vehicle;
        this.vehicleInfo = vehicleInfo;
        loop();
    }

    @Override
    protected void initialize() {
        addOption("Show Car Details", () -> System.out.println(vehicle + "\n" + vehicleInfo));
        addOption("Start Reservation", () -> addReservation());
    }

    private void addReservation() {
        Date finalStartDate, finalEndDate;

        while (true) {
            String startDate, endDate;

            Date startDateObj, endDateObj;

            System.out.print("Enter starting date [dd-mm-yyyy]: ");
            startDate = scanner.next();

            String[] a = startDate.split("-");

            Integer[] startDateNumbers;

            try {
                startDateNumbers = utils.Utils.processLine(a);
            } catch (NumberFormatException e) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            if (startDateNumbers.length != 3)
                continue;

            try {
                startDateObj = new Date(startDateNumbers[0], startDateNumbers[1], startDateNumbers[2]);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            if (startDateObj.IsBefore(Turo.CurrentDate)) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            System.out.print("Enter ending date [dd-mm-yyyy]: ");
            endDate = scanner.next();

            String[] b = endDate.split("-");

            Integer[] endDateNumbers;

            try {
                endDateNumbers = utils.Utils.processLine(b);
            } catch (NumberFormatException e) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            if (endDateNumbers.length != 3)
                continue;

            try {
                endDateObj = new Date(endDateNumbers[0], endDateNumbers[1], endDateNumbers[2]);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }


            if (endDateObj.IsBefore(Turo.CurrentDate)) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            if (endDateObj.IsBefore(startDateObj)) {
                System.out.println("Invalid date passed, please try again.");
                continue;
            }

            if (Date.CountDays(startDateObj, endDateObj).intValue() > 28) {
                System.out.println("Maximum days are 28. You Entered " + Date.CountDays(startDateObj, endDateObj).intValue() + ".");
                continue;
            }

            finalStartDate = startDateObj;
            finalEndDate = endDateObj;
            break;
        }


        new CarInsuranceMenu(vehicle.getPlate(), finalStartDate, finalEndDate);
    }
}
