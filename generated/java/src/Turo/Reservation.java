package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Reservation {
  private Booking Booking = null;
  private Transaction Transaction = null;
  private Number Price = 0L;
  private Date StartDate;
  private Date EndDate;

  public void cg_init_Reservation_1(
      final Insurance insurance, final Date startDate, final Date endDate) {

    Number numDays = Date.CountDays(startDate, endDate);
    Price =
        numDays.longValue() * insurance.getPrice().doubleValue()
            + insurance.getDepositValue().doubleValue();
    StartDate = startDate;
    EndDate = endDate;
    return;
  }

  public Reservation(final Insurance insurance, final Date startDate, final Date endDate) {

    cg_init_Reservation_1(insurance, startDate, endDate);
  }

  public Number getPrice() {

    return Price;
  }

  public Boolean isPayed() {

    if (!(Utils.equals(Transaction, null))) {
      return true;

    } else {
      return false;
    }
  }

  public Number getDuration() {

    return Date.CountDays(StartDate, EndDate);
  }

  public void setPayed(final Transaction transaction) {

    Turo.addTransaction(transaction);
    Transaction = transaction;
  }

  public void addBooking(final Booking booking) {

    Booking = booking;
  }

  public Booking getBooking() {

    return Booking;
  }

  public Date getStartDate() {

    return StartDate;
  }

  public Date getEndDate() {

    return EndDate;
  }

  public Reservation() {}

  public String toString() {

    return "Reservation{"
        + "Booking := "
        + Utils.toString(Booking)
        + ", Transaction := "
        + Utils.toString(Transaction)
        + ", Price := "
        + Utils.toString(Price)
        + ", StartDate := "
        + Utils.toString(StartDate)
        + ", EndDate := "
        + Utils.toString(EndDate)
        + "}";
  }
}
