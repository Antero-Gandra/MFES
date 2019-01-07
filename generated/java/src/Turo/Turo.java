package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Turo {
  private static VDMSet users = SetUtil.set();
  private static VDMSet carOwners = SetUtil.set();
  private static VDMSet insurances = SetUtil.set();
  private static VDMSet transactions = SetUtil.set();
  public static Date CurrentDate = new Date(7L, 1L, 2019L);
  private static Number cancellationFee = 50L;

  public void cg_init_Turo_1() {

    return;
  }

  public Turo() {

    cg_init_Turo_1();
  }

  public void addUser(final User user) {

    users = SetUtil.union(Utils.copy(Turo.users), SetUtil.set(user));
  }

  public void addCarOwner(final CarOwner user) {

    carOwners = SetUtil.union(Utils.copy(Turo.carOwners), SetUtil.set(user));
  }

  public void addInsurance(final Insurance insurance) {

    insurances = SetUtil.union(Utils.copy(Turo.insurances), SetUtil.set(insurance));
  }

  public static void addTransaction(final Transaction transaction) {

    transactions = SetUtil.union(Utils.copy(Turo.transactions), SetUtil.set(transaction));
  }

  public Insurance getInsurance(final Object grade) {

    Insurance insurance = null;
    for (Iterator iterator_10 = Turo.insurances.iterator(); iterator_10.hasNext(); ) {
      Insurance o = (Insurance) iterator_10.next();
      if (Utils.equals(grade, o.getInsuranceType())) {
        insurance = o;
      }
    }
    return insurance;
  }

  public VDMSet getUsers() {

    return Utils.copy(Turo.users);
  }

  public VDMSet getCarOwners() {

    return Utils.copy(Turo.carOwners);
  }

  public VDMSet getInsurances() {

    return Utils.copy(Turo.insurances);
  }

  public VDMSet getTransactions() {

    return Utils.copy(Turo.transactions);
  }

  public static Number getCancelFee() {

    return Turo.cancellationFee;
  }

  public User getUser(final String email) {

    User user = null;
    VDMSet allUsers = SetUtil.union(Utils.copy(Turo.users), Utils.copy(Turo.carOwners));
    for (Iterator iterator_11 = allUsers.iterator(); iterator_11.hasNext(); ) {
      User o = (User) iterator_11.next();
      if (Utils.equals(email, o.getEmail())) {
        user = o;
      }
    }
    return user;
  }

  public static CarOwner getUser(final Vehicle vehicle) {

    CarOwner user = null;
    for (Iterator iterator_12 = Turo.carOwners.iterator(); iterator_12.hasNext(); ) {
      CarOwner o = (CarOwner) iterator_12.next();
      for (Iterator iterator_13 = o.getCars().iterator(); iterator_13.hasNext(); ) {
        Vehicle c = (Vehicle) iterator_13.next();
        if (Utils.equals(vehicle, c)) {
          user = o;
        }
      }
    }
    return user;
  }

  public static User getUserByBooking(final Booking booking) {

    User user = null;
    VDMSet allUsers = SetUtil.union(Utils.copy(Turo.users), Utils.copy(Turo.carOwners));
    for (Iterator iterator_14 = allUsers.iterator(); iterator_14.hasNext(); ) {
      User o = (User) iterator_14.next();
      if (!(Utils.equals(o.getReservation(), null))) {
        if (Utils.equals(booking, o.getReservation().getBooking())) {
          user = o;
        }
      }
    }
    return user;
  }

  public Vehicle getVehicle(final String plate) {

    CarOwner owner = null;
    Vehicle car = null;
    for (Iterator iterator_15 = Turo.carOwners.iterator(); iterator_15.hasNext(); ) {
      CarOwner o = (CarOwner) iterator_15.next();
      for (Iterator iterator_16 = o.getCars().iterator(); iterator_16.hasNext(); ) {
        Vehicle c = (Vehicle) iterator_16.next();
        if (Utils.equals(plate, c.getPlate())) {
          owner = o;
          car = c;
        }
      }
    }
    return car;
  }

  public static Vehicle getVehicle(final Reservation reservation) {

    CarOwner owner = null;
    Vehicle car = null;
    for (Iterator iterator_17 = Turo.carOwners.iterator(); iterator_17.hasNext(); ) {
      CarOwner o = (CarOwner) iterator_17.next();
      for (Iterator iterator_18 = o.getCars().iterator(); iterator_18.hasNext(); ) {
        Vehicle c = (Vehicle) iterator_18.next();
        if (SetUtil.inSet(reservation, c.getReservations())) {
          owner = o;
          car = c;
        }
      }
    }
    return car;
  }

  public static Insurance getInsuranceByReservation(final Reservation reservation) {

    Insurance insurance = null;
    for (Iterator iterator_19 = Turo.insurances.iterator(); iterator_19.hasNext(); ) {
      Insurance i = (Insurance) iterator_19.next();
      if (SetUtil.inSet(reservation, i.getReservations())) {
        insurance = i;
      }
    }
    return insurance;
  }

  public static Vehicle getVehicleByBooking(final Booking booking) {

    CarOwner owner = null;
    Vehicle car = null;
    Reservation reservation = null;
    for (Iterator iterator_20 = Turo.carOwners.iterator(); iterator_20.hasNext(); ) {
      CarOwner o = (CarOwner) iterator_20.next();
      for (Iterator iterator_21 = o.getCars().iterator(); iterator_21.hasNext(); ) {
        Vehicle c = (Vehicle) iterator_21.next();
        if (c.getReservations().size() > 0L) {
          for (Iterator iterator_22 = c.getReservations().iterator(); iterator_22.hasNext(); ) {
            Reservation r = (Reservation) iterator_22.next();
            if (Utils.equals(booking, r.getBooking())) {
              owner = o;
              reservation = r;
              car = c;
            }
          }
        }
      }
    }
    return car;
  }

  public String toString() {

    return "Turo{"
        + "users := "
        + Utils.toString(users)
        + ", carOwners := "
        + Utils.toString(carOwners)
        + ", insurances := "
        + Utils.toString(insurances)
        + ", transactions := "
        + Utils.toString(transactions)
        + ", CurrentDate := "
        + Utils.toString(CurrentDate)
        + ", cancellationFee := "
        + Utils.toString(cancellationFee)
        + "}";
  }
}
