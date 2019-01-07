package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CarOwner extends User {
  private VDMSet cars = SetUtil.set();
  private Number Rating = 0L;
  private Number TripNumber = 0L;

  public void cg_init_CarOwner_1(
      final String name,
      final String email,
      final Number phone,
      final String location,
      final String license,
      final Date birth) {

    Name = name;
    Email = email;
    PhoneNumber = phone;
    Location = location;
    License = license;
    BirthDate = birth;
    return;
  }

  public CarOwner(
      final String name,
      final String email,
      final Number phone,
      final String location,
      final String license,
      final Date birth) {

    cg_init_CarOwner_1(name, email, phone, location, license, birth);
  }

  public void addVehicle(final Vehicle vehicle) {

    cars = SetUtil.union(Utils.copy(cars), SetUtil.set(vehicle));
  }

  public VDMSet getCars() {

    return Utils.copy(cars);
  }

  public Number getNumberTrips() {

    return TripNumber;
  }

  public void calculateRating() {

    Number sum = 0L;
    for (Iterator iterator_9 = cars.iterator(); iterator_9.hasNext(); ) {
      Vehicle c = (Vehicle) iterator_9.next();
      sum = sum.doubleValue() + c.getRating().doubleValue();
    }
    if (Utils.equals(TripNumber, 0L)) {
      Rating = 0L;
    } else {
      Rating = Utils.divide(sum.doubleValue(), TripNumber.longValue());
    }
  }

  public Number getRating() {

    return Rating;
  }

  public void incrementTrips() {

    TripNumber = TripNumber.longValue() + 1L;
  }

  public CarOwner() {}

  public String toString() {

    return "CarOwner{"
        + "cars := "
        + Utils.toString(cars)
        + ", Rating := "
        + Utils.toString(Rating)
        + ", TripNumber := "
        + Utils.toString(TripNumber)
        + "}";
  }
}
