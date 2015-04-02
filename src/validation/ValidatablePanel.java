package validation;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ValidatablePanel extends JPanel implements Validatable {
  private static final long serialVersionUID = 8503861490354762582L;

  private JComponent save = null;
  private JComponent update = null;

  @Override
  public void validationFailed() {
    if (save != null) {
      save.setEnabled(false);
    }
    if (update != null) {
      update.setEnabled(false);
    }
  }

  @Override
  public void validationPassed() {
    if (save != null) {
      save.setEnabled(true);
    }
    if (update != null) {
      update.setEnabled(true);
    }
  }

  public void setButtonsToBeAffectedAfterValidation(JComponent save) {
    this.save = save;
  }

  public void setButtonsToBeAffectedAfterValidation(JComponent save, JComponent update) {
    this.save = save;
    this.update = update;
  }
}
