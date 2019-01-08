package vdm.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BASICQuote {
  private static int hc = 0;
  private static BASICQuote instance = null;

  public BASICQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BASICQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BASICQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BASICQuote;
  }

  public String toString() {

    return "<BASIC>";
  }
}
