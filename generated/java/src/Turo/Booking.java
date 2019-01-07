package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Booking {
  private Transaction Transaction = null;
  private Number Price = 0L;
  private Number FuelPrice = 0L;
  private Number FuelUsed = 0L;

  public void cg_init_Booking_1(
      final Number daysPrice, final Number fuelPrice, final Number fuelUsed) {

    Price = daysPrice;
    FuelPrice = fuelPrice;
    FuelUsed = fuelUsed;
    return;
  }

  public Booking(final Number daysPrice, final Number fuelPrice, final Number fuelUsed) {

    cg_init_Booking_1(daysPrice, fuelPrice, fuelUsed);
  }

  public Number getTotalPrice() {

    return Price.doubleValue() + FuelPrice.doubleValue() * FuelUsed.doubleValue();
  }

  public Boolean isPayed() {

    if (!(Utils.equals(Transaction, null))) {
      return true;

    } else {
      return false;
    }
  }

  public void setPayed(final Transaction transaction) {

    User user = Turo.getUserByBooking(this);
    Vehicle vehicle = Turo.getVehicleByBooking(this);
    Insurance insurance = Turo.getInsuranceByReservation(user.getReservation());
    Turo.addTransaction(transaction);
    insurance.removeReservation(user.getReservation());
    vehicle.deliver(user.getReservation());
    user.removeReservation();
    Transaction = transaction;
  }

  public Booking() {}

  public String toString() {

    return "Booking{"
        + "Transaction := "
        + Utils.toString(Transaction)
        + ", Price := "
        + Utils.toString(Price)
        + ", FuelPrice := "
        + Utils.toString(FuelPrice)
        + ", FuelUsed := "
        + Utils.toString(FuelUsed)
        + "}";
  }
}
