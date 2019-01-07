package Turo.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class INTERMEDIATEQuote {
  private static int hc = 0;
  private static INTERMEDIATEQuote instance = null;

  public INTERMEDIATEQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static INTERMEDIATEQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new INTERMEDIATEQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof INTERMEDIATEQuote;
  }

  public String toString() {

    return "<INTERMEDIATE>";
  }
}
