package validation;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoubleValidator extends AbstractValidator {

  private double minValue = Double.MIN_VALUE;
  private double maxValue = Double.MAX_VALUE;
  private boolean nullable;
  private boolean checkForMinMax = false;

  public DoubleValidator(JPanel parent, JTextField component, String errorMessage, boolean nullable) {
    super(parent, component, errorMessage);
    this.nullable = nullable;
  }

  public DoubleValidator(JPanel parent, JTextField component, String errorMessage, boolean nullable, double minValue, double maxValue) {
    super(parent, component, errorMessage);
    this.nullable = nullable;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.checkForMinMax = true;
  }

  @Override
  public boolean validationCriteria(JComponent component) {
    if (SpecialParser.parseDouble(((JTextField) component).getText(), nullable)) {
      if (((JTextField) component).getText() != null
          && !((JTextField) component).getText().isEmpty()) {
        double value = Double.parseDouble(((JTextField) component).getText());
        if (checkForMinMax) {
          if (value >= minValue && value <= maxValue) {
            return true;
          }
          return false;
        }
        return true;
      }
      return true;
    } else {
      return false;
    }
  }
}
