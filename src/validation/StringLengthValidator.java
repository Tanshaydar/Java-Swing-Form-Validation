package validation;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StringLengthValidator extends AbstractValidator {
  private int maks = Integer.MAX_VALUE;

  public StringLengthValidator(JPanel parent, JTextArea component, String errorMessage, int max) {
    super(parent, component, errorMessage);
    this.maks = max;
  }

  public StringLengthValidator(JPanel parent, JTextField component, String errorMessage, int max) {
    super(parent, component, errorMessage);
    this.maks = max;
  }

  @Override
  public boolean validationCriteria(JComponent component) {
    if (component instanceof JTextArea) {
      if (((JTextArea) component).getText().isEmpty() || ((JTextArea) component).getText() == null) {
        return true;
      } else if (((JTextArea) component).getText().length() > maks) {
        return false;
      }
      return true;
    } else if (component instanceof JTextField) {
      if (((JTextField) component).getText().isEmpty()
          || ((JTextField) component).getText() == null) {
        return true;
      } else if (((JTextField) component).getText().length() > maks) {
        return false;
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean shouldYieldFocus(JComponent input) {
    verify(input);
    return true;
  }
}
