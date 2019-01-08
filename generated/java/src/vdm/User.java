package vdm;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  private Reservation creates = null;
  protected String Name = SeqUtil.toStr(SeqUtil.seq());
  protected String Email = SeqUtil.toStr(SeqUtil.seq());
  protected Number PhoneNumber = 0L;
  protected String Location = SeqUtil.toStr(SeqUtil.seq());
  protected Date BirthDate;
  protected String License = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_User_1(
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

  public User(
      final String name,
      final String email,
      final Number phone,
      final String location,
      final String license,
      final Date birth) {

    cg_init_User_1(name, email, phone, location, license, birth);
  }

  public String getEmail() {

    return Email;
  }

  public Reservation getReservation() {

    return creates;
  }

  public void addReservation(final Reservation reservation) {

    creates = reservation;
  }

  public void removeReservation() {

    creates = null;
  }

  public void cancelReservation() {

    Vehicle vehicle = Turo.getVehicle(creates);
    Transaction transaction = new Transaction(Turo.getCancelFee(), Turo.CurrentDate);
    Turo.addTransaction(transaction);
    vehicle.removeReservation(creates);
    creates = null;
  }

  public void deliverVehicle(
      final Number fuelPrice,
      final Number fuelUsed,
      final Number carRating,
      final Number odometer) {

    Number numDays = creates.getDuration();
    Vehicle resVehicle = Turo.getVehicle(creates);
    Booking booking =
        new Booking(numDays.longValue() * resVehicle.getPrice().doubleValue(), fuelPrice, fuelUsed);
    resVehicle.addRating(carRating);
    resVehicle.addOdometer(odometer);
    creates.addBooking(booking);
  }

  public User() {}

  public String toString() {

    return "User{"
        + "creates := "
        + Utils.toString(creates)
        + ", Name := "
        + Utils.toString(Name)
        + ", Email := "
        + Utils.toString(Email)
        + ", PhoneNumber := "
        + Utils.toString(PhoneNumber)
        + ", Location := "
        + Utils.toString(Location)
        + ", BirthDate := "
        + Utils.toString(BirthDate)
        + ", License := "
        + Utils.toString(License)
        + "}";
  }
}
