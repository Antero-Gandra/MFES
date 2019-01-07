package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private Number Day;
  private Number Month;
  private Number Year;

  public Date(final Number day, final Number month, final Number year) {

    throw new UnsupportedOperationException();
  }

  public static Boolean ValidAge(final Date dateToCheck, final Date currentDate) {

    if (currentDate.Year.longValue() - dateToCheck.Year.longValue() < 21L) {
      return false;
    }

    if (currentDate.Year.longValue() - dateToCheck.Year.longValue() > 21L) {
      return true;
    }

    if (dateToCheck.Month.longValue() < currentDate.Month.longValue()) {
      return true;
    }

    if (dateToCheck.Month.longValue() > currentDate.Month.longValue()) {
      return false;
    }

    if (dateToCheck.Day.longValue() <= currentDate.Day.longValue()) {
      return true;
    }

    if (dateToCheck.Day.longValue() > currentDate.Day.longValue()) {
      return false;
    }

    return false;
  }

  public static Number CountDays(final Date beginDate, final Date endDate) {

    Number daysDateBegin = 0L;
    Number daysDateEnd = 0L;
    if (Utils.equals(beginDate.Month, endDate.Month)) {
      return endDate.Day.longValue() - beginDate.Day.longValue();
    }

    return DaysOfMonth(beginDate.Month, beginDate.Year).longValue()
        - beginDate.Day.longValue()
        + endDate.Day.longValue();
  }

  public Boolean IsBefore(final Date otherDate) {

    throw new UnsupportedOperationException();
  }

  public Number getDay() {

    return Day;
  }

  public Number getMonth() {

    return Month;
  }

  public Number getYear() {

    return Year;
  }

  public Date() {}

  public static Boolean IsLeapYear(final Number year) {

    throw new UnsupportedOperationException();
  }

  public static Number DaysOfMonth(final Number month, final Number year) {

    throw new UnsupportedOperationException();
  }

  public String toString() {

    return "Date{"
        + "Day := "
        + Utils.toString(Day)
        + ", Month := "
        + Utils.toString(Month)
        + ", Year := "
        + Utils.toString(Year)
        + "}";
  }
}
