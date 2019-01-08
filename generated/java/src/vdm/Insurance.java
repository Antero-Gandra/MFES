package vdm;

import vdm.quotes.BASICQuote;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Insurance {
  private VDMSet reservationInsurance = SetUtil.set();
  private Object InsuranceType = BASICQuote.getInstance();
  private Number Price = 0L;
  private Number DepositValue = 500L;

  public void cg_init_Insurance_1(final Object grade, final Number price, final Number deposit) {

    InsuranceType = grade;
    Price = price;
    DepositValue = deposit;
    return;
  }

  public Insurance(final Object grade, final Number price, final Number deposit) {

    cg_init_Insurance_1(grade, price, deposit);
  }

  public Object getInsuranceType() {

    return InsuranceType;
  }

  public Number getPrice() {

    return Price;
  }

  public Number getDepositValue() {

    return DepositValue;
  }

  public VDMSet getReservations() {

    return Utils.copy(reservationInsurance);
  }

  public void addReservation(final Reservation reservation) {

    reservationInsurance =
        SetUtil.union(Utils.copy(reservationInsurance), SetUtil.set(reservation));
  }

  public void removeReservation(final Reservation reservation) {

    reservationInsurance = SetUtil.diff(Utils.copy(reservationInsurance), SetUtil.set(reservation));
  }

  public Insurance() {}

  public String toString() {

    return "Insurance{"
        + "reservationInsurance := "
        + Utils.toString(reservationInsurance)
        + ", InsuranceType := "
        + Utils.toString(InsuranceType)
        + ", Price := "
        + Utils.toString(Price)
        + ", DepositValue := "
        + Utils.toString(DepositValue)
        + "}";
  }
}
