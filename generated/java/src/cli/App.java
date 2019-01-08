package cli;

import cli.Owner.OwnerMenu;
import cli.User.UserMenu;
import instance.TuroSingleton;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;
import vdm.CarOwner;
import vdm.Insurance;
import vdm.Turo;
import vdm.User;

import java.util.Iterator;
import java.util.Scanner;

public class App {

    private Turo turo = TuroSingleton.getTuroInstance();
    private static String email;
    private static Scanner scanner;

    public App() {

        scanner = new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("*  Welcome to Turo                                   *");
        System.out.println("******************************************************");


        while (true) {

            /* Logged in user type
             * 0 - not logged in
              * 1 - normal user
              * 2 - car owner
             */
            int loggedIn;

            do {
                System.out.print("\nEnter your user email: ");
                email = scanner.next();
                loggedIn = login(email);
            } while (loggedIn != 1 && loggedIn != 2 && loggedIn != 3);

            if (loggedIn == 1) {
                new UserMenu();
            }

            if (loggedIn == 2) {
                new OwnerMenu();
            }
        }
    }

    private int login(String email) {

        if (email == "admin@feup.pt") {
            return 3;
        }

        /* Is normal user */
        VDMSet appUsers = turo.getUsers();
        for (Iterator iterator_10 = appUsers.iterator(); iterator_10.hasNext(); ) {
            User o = (User) iterator_10.next();
            if (Utils.equals(email, o.getEmail())) {
                return 1;
            }
        }

        /* Is normal user */
        VDMSet appCarOwners = turo.getCarOwners();
        for (Iterator iterator_10 = appCarOwners .iterator(); iterator_10.hasNext(); ) {
            CarOwner o = (CarOwner) iterator_10.next();
            if (Utils.equals(email, o.getEmail())) {
                return 2;
            }
        }

        return 0;
    }

    public static String getEmail() {
        return email;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void main(String[] args) {
        new App();
    }
}
