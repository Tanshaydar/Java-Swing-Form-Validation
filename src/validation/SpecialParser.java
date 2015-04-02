package validation;

public class SpecialParser {
  public static boolean parseInt(String string, boolean nullable) {
    if (nullable) {
      if (string == null || string.isEmpty()) {
        return true;
      }
    } else {
      if (string == null || string.isEmpty()) {
        return false;
      }
    }

    try {
      Integer.parseInt(string);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public static boolean parseDouble(String string, boolean nullable) {
    if (nullable) {
      if (string == null || string.isEmpty()) {
        return true;
      }
    } else {
      if (string == null || string.isEmpty()) {
        return false;
      }
    }

    try {
      Double.parseDouble(string);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
