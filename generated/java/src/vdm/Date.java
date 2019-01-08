package vdm;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private Number Day;
  private Number Month;
  private Number Year;

  public Date(final Number day, final Number month, final Number year) {

    if (!(month.intValue() <= 12 && day.intValue() <= 31 && day.intValue() <= DaysOfMonth(month, year)))
      throw new IllegalArgumentException();

    Day = day;
    Month = month;
    Year = year;
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

    if (Utils.equals(beginDate.Month, endDate.Month)) {
      return endDate.Day.intValue() - beginDate.Day.intValue();
    }

    return DaysOfMonth(beginDate.Month, beginDate.Year.intValue())
        - beginDate.Day.intValue()
        + endDate.Day.intValue();
  }

  public Boolean IsBefore(final Date otherDate) {

    if (otherDate.Year.intValue() > Year.intValue())
      return true;
    else
      if (otherDate.Year.intValue() == Year.intValue())
        if (otherDate.Month.intValue() > Month.intValue())
          return true;
        else
          if (otherDate.Month.intValue() == Month.intValue())
            if (otherDate.Day.intValue() > Day.intValue())
              return true;
            else
              return false;
          else
            return false;
      else
        return false;
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

    return (year.intValue() % 4 == 0 && year.intValue() % 100 != 0 || year.intValue() % 400 == 0);
  }

  public static int DaysOfMonth(final Number month, final Number year) {

    if (month.intValue() == 2)
      if (IsLeapYear(year.intValue()))
        return 29;
      else
        return 28;
    else
      return 31 - ((month.intValue() - 1) % 7) % 2;
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
