package cli.User;

import cli.App;
import cli.Menu;
import instance.TuroSingleton;
import vdm.*;

import java.util.InputMismatchException;

public class ListUserReservation extends Menu {

    public ListUserReservation() {
        loop();
    }

    @Override
    protected void initialize() {
        Turo turo = TuroSingleton.getTuroInstance();

        User user = turo.getUser(App.getEmail());

        Reservation reservation = user.getReservation();

        if (reservation != null) {
            System.out.println(reservation);

            Booking booking = reservation.getBooking();
            if (booking == null) {
                addOption("Cancel Reservation", () -> {
                    user.cancelReservation();
                    new UserMenu();
                });
                addOption("Deliver Vehicle", () -> {
                    deliverCar(user);
                    reinitialize();
                });
            } else {
                addOption("Pay Booking", () -> {
                    payBooking(reservation, booking);
                    System.out.println("----------------------------");
                    reinitialize();
                });
            }
        } else {
            System.out.println("Oops! You do not have a reservation");
        }
    }

    private void payBooking(Reservation reservation, Booking booking) {
        String startDate;
        Date startDateObj;

        while (true) {

            System.out.print("Enter paying date [dd-mm-yyyy]: ");
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


            if (startDateObj.IsBefore(reservation.getEndDate())) {
                System.out.println("That date is before car delivery.");
                continue;
            }


            break;
        }

        System.out.println("--------------------");
        Transaction transaction = new Transaction(booking.getTotalPrice(), startDateObj);
        booking.setPayed(transaction);
    }

    private void deliverCar(User user) {
        Integer rating = 0;
        Double fuelPrice = 0.0;
        Double fuelUsed = 0.0;
        Double odometer = 0.0;

        while (true) {

            /* Ask rating */
            System.out.print("How do you rate the car [0 - 5]: ");

            try {
                rating = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            if (rating < 0 || rating > 5) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            /* Ask fuel price */
            System.out.print("What is the fuel price:  ");

            try {
                fuelPrice = scanner.nextDouble();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            } catch (InputMismatchException e1) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            if (fuelPrice < 0) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            /* Ask fuel used */
            System.out.print("How much fuel was used:  ");

            try {
                fuelUsed = scanner.nextDouble();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            } catch (InputMismatchException e1) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            if (fuelUsed < 0) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            /* Ask fuel used */
            System.out.print("How many kilometers were driven:  ");

            try {
                odometer = scanner.nextDouble();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            } catch (InputMismatchException e1) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            if (odometer < 0) {
                System.out.println("Invalid number passed, please try again.");
                continue;
            }

            break;
        }

        user.deliverVehicle(fuelPrice, fuelUsed, rating, odometer);
    }
}
