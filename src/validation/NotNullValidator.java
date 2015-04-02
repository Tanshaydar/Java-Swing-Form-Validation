package validation;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NotNullValidator extends AbstractValidator {
  private int maks = Integer.MAX_VALUE;

  public NotNullValidator(JPanel parent, JTextField component, String errorMessage) {
    super(parent, component, errorMessage);
  }
  
  public NotNullValidator(JPanel parent, JTextField component, String errorMessage, int maks) {
    super(parent, component, errorMessage);
    this.maks = maks;
  }

  @Override
  public boolean validationCriteria(JComponent component) {
    if (((JTextField) component).getText().isEmpty() || ((JTextField) component).getText() == null) {
      return false;
    } else if (((JTextField) component).getText().length() > maks) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean shouldYieldFocus(JComponent input) {
    verify(input);
    return true;
  }

}
