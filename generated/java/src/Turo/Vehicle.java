package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Vehicle {
  private VehicleInfo vehicleInfo = null;
  private VDMSet reservations = SetUtil.set();
  private Number DailyPrice = 0L;
  private String Description = SeqUtil.toStr(SeqUtil.seq());
  private Number Rating = 0L;
  private Number NumberTrips = 0L;
  private String Plate = SeqUtil.toStr(SeqUtil.seq());
  private Number Odometer = 0L;

  public void cg_init_Vehicle_1(
      final VehicleInfo info,
      final Number price,
      final String description,
      final String plate,
      final Number odometer) {

    vehicleInfo = info;
    DailyPrice = price;
    Description = description;
    Plate = plate;
    Odometer = odometer;
    return;
  }

  public Vehicle(
      final VehicleInfo info,
      final Number price,
      final String description,
      final String plate,
      final Number odometer) {

    cg_init_Vehicle_1(info, price, description, plate, odometer);
  }

  public String getPlate() {

    return Plate;
  }

  public Number getNumberTrips() {

    return NumberTrips;
  }

  public Number getPrice() {

    return DailyPrice;
  }

  public Number getRating() {

    return Rating;
  }

  public VDMSet getReservations() {

    return Utils.copy(reservations);
  }

  public void addOdometer(final Number odometer) {

    Odometer = Odometer.longValue() + odometer.longValue();
  }

  public Number getOdometer() {

    return Odometer;
  }

  public void addReservation(final Reservation reservation) {

    reservations = SetUtil.union(Utils.copy(reservations), SetUtil.set(reservation));
  }

  public void addRating(final Number rating) {

    Rating =
        Utils.divide(
            Rating.doubleValue() * NumberTrips.longValue() + rating.longValue(),
            NumberTrips.longValue() + 1L);
  }

  public void removeReservation(final Reservation reservation) {

    reservations = SetUtil.diff(Utils.copy(reservations), SetUtil.set(reservation));
  }

  public void deliver(final Reservation reservation) {

    CarOwner owner = Turo.getUser(this);
    owner.incrementTrips();
    owner.calculateRating();
    NumberTrips = NumberTrips.longValue() + 1L;
    reservations = SetUtil.diff(Utils.copy(reservations), SetUtil.set(reservation));
  }

  public Vehicle() {}

  public String toString() {

    return "Vehicle{"
        + "vehicleInfo := "
        + Utils.toString(vehicleInfo)
        + ", reservations := "
        + Utils.toString(reservations)
        + ", DailyPrice := "
        + Utils.toString(DailyPrice)
        + ", Description := "
        + Utils.toString(Description)
        + ", Rating := "
        + Utils.toString(Rating)
        + ", NumberTrips := "
        + Utils.toString(NumberTrips)
        + ", Plate := "
        + Utils.toString(Plate)
        + ", Odometer := "
        + Utils.toString(Odometer)
        + "}";
  }
}
