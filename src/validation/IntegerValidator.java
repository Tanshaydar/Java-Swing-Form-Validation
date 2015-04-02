package validation;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IntegerValidator extends AbstractValidator {

  private int minValue = Integer.MIN_VALUE;
  private int maxValue = Integer.MAX_VALUE;
  private boolean nullable;
  private boolean checkForMinMax = false;

  public IntegerValidator(JPanel parent, JTextField component, String text, boolean nullable) {
    super(parent, component, text);
    this.nullable = nullable;
  }

  public IntegerValidator(JPanel parent, JTextField component, String text, boolean nullable,
      int minValue, int maxValue) {
    super(parent, component, text);
    this.nullable = nullable;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.checkForMinMax = true;
  }

  @Override
  public boolean validationCriteria(JComponent component) {
    if (SpecialParser.parseInt(((JTextField) component).getText(), nullable)) {
      if (((JTextField) component).getText() != null
          && !((JTextField) component).getText().isEmpty()) {
        int value = Integer.parseInt(((JTextField) component).getText());
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
