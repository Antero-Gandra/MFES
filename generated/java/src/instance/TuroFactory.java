package instance;

import vdm.*;

import java.util.ArrayList;
import vdm.quotes.BASICQuote;
import vdm.quotes.INTERMEDIATEQuote;
import vdm.quotes.MAXIMUMQuote;

public class TuroFactory {

    public static final String[] userNames = {"Igor", "Antero", "João", "Luís"};
    public static final String[] userEmails = {"igor@feup.pt", "antero@feup.pt", "joao@feup.pt", "luis@feup.pt"};
    public static final Number[] userPhones = {912345678, 914627382, 934678215, 927428392};
    public static final String[] userLicences = {"H-27SD-D", "H-21DAA-S", "P-SD193-1", "T-32AS9-2"};
    public static final Date[] birthDates = {new Date(1, 2, 1997), new Date(9, 10, 1996), new Date(30, 1, 1997), new Date(4, 6, 1995)};

    public static final VehicleInfo[] vehicleInfos = {
            new VehicleInfo(2, 3, "Corsa", 2015, "Diesel", "Hatchback", "Opel", "Blue"),
            new VehicleInfo(2, 3, "SLK", 2008, "Diesel", "Coupe", "Mercedes", "Grey"),
            new VehicleInfo(5, 5, "A5", 2016, "Diesel", "Sedan", "Audi", "Black")
    };

    public static final String[] vehicleDescriptions = {
            "Practical, very economic and good cargo space",
            "For the speed lovers",
            "For the big families with extra comfort",
    };

    public static final String[] vehiclePlates = {"DA-21-SS", "SA-AD-31", "23-DA-AA"};
    public static final Number[] odometers = {120390, 80290, 43019};
    public static final Number[] dailyPrices = {19.9, 45, 34.5};

    public static ArrayList<Vehicle> cars = new ArrayList();

    public static Turo getTuro() {
        Turo turo = new Turo();
        setDefault(turo);
        return turo;
    }

    private static void setDefault(Turo turo) {
        createCars();
        addUsers(turo);
        addInsurances(turo);
        addReservations(turo);
    }

    private static void addReservations(Turo turo) {
        Date startDate = new Date(2, 2, 2019), endDate = new Date(7, 2, 2019);
        Insurance insurance = turo.getInsurance(new BASICQuote());
        User user = turo.getUser("antero@feup.pt");
        Vehicle vehicle = turo.getVehicle("SA-AD-31");

        Reservation reservation = new Reservation(insurance, startDate, endDate);

        insurance.addReservation(reservation);
        user.addReservation(reservation);
        vehicle.addReservation(reservation);
    }

    private static void addInsurances(Turo turo) {
        turo.addInsurance(new Insurance(new BASICQuote(), 0, 500));
        turo.addInsurance(new Insurance(new INTERMEDIATEQuote(), 10, 300));
        turo.addInsurance(new Insurance(new MAXIMUMQuote(), 20, 0));
    }

    private static void createCars() {
        for (int i = 0; i < vehicleInfos.length; i++) {
            Vehicle vehicle = new Vehicle(vehicleInfos[i], dailyPrices[i], vehicleDescriptions[i], vehiclePlates[i], odometers[i]);
            cars.add(vehicle);
        }
    }

    private static void addUsers(Turo turo) {

        /* Add regular users */
        for (int i = 0; i < 2; i++) {
            User user = new User(userNames[i], userEmails[i], userPhones[i], userLicences[i], "Porto", birthDates[i]);
            turo.addUser(user);
        }

        /* Add regular users */
        for (int i = 2; i < userNames.length; i++) {
            CarOwner user = new CarOwner(userNames[i], userEmails[i], userPhones[i], userLicences[i], "Porto", birthDates[i]);
            if (i == 2){
                user.addVehicle(cars.get(0));
                user.addVehicle(cars.get(1));
            } else if (i == 3){
                user.addVehicle(cars.get(2));
            }

            turo.addCarOwner(user);
        }
    }
}
