package Turo;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Transaction {
  private Number Amount = 0L;
  private static Number idCounter = 0L;
  private Number Reference = 0L;
  private Date TransDate;

  public void cg_init_Transaction_1(final Number amount, final Date date) {

    Amount = amount;
    idCounter = Transaction.idCounter.longValue() + 1L;
    Reference = Transaction.idCounter;
    TransDate = date;
    return;
  }

  public Transaction(final Number amount, final Date date) {

    cg_init_Transaction_1(amount, date);
  }

  public Date getDate() {

    return TransDate;
  }

  public Number getId() {

    return Reference;
  }

  public Transaction() {}

  public String toString() {

    return "Transaction{"
        + "Amount := "
        + Utils.toString(Amount)
        + ", idCounter := "
        + Utils.toString(idCounter)
        + ", Reference := "
        + Utils.toString(Reference)
        + ", TransDate := "
        + Utils.toString(TransDate)
        + "}";
  }
}
