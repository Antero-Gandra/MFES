package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MAXIMUMQuote {
  private static int hc = 0;
  private static MAXIMUMQuote instance = null;

  public MAXIMUMQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MAXIMUMQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MAXIMUMQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MAXIMUMQuote;
  }

  public String toString() {

    return "<MAXIMUM>";
  }
}
