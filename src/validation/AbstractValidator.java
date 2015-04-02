package validation;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.Border;

public abstract class AbstractValidator extends InputVerifier implements KeyListener, FocusListener {

  private JComponent component;
  private Object parent;
  private JLabel message;
  private JLabel errorIcon;

  private Border errorBorder = BorderFactory.createLineBorder(Color.RED);
  private Color errorColor;

  private AbstractValidator() {
    errorColor = new Color(243, 255, 159);
  }

  private AbstractValidator(JComponent component, String errorMessage) {
    this();
    this.component = component;
    component.addKeyListener(this);
    component.addFocusListener(this);
    message = new JLabel(errorMessage + " ");
    component.setToolTipText(message.getText());
    ToolTipManager.sharedInstance().setInitialDelay(0);
    try {
      errorIcon = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/images/unverified.png"))));
    } catch (IOException ex) {
      System.err.println(ex.toString());
    }
  }

  protected AbstractValidator(JPanel parent, JComponent component, String errorMessage) {
    this(component, errorMessage);
    if (parent != null) {
      this.parent = parent;
    } else {
      this.parent = component.getParent();
    }
  }

  protected AbstractValidator(JFrame parent, JComponent component, String errorMessage) {
    this(component, errorMessage);
    this.parent = parent;
  }

  protected abstract boolean validationCriteria(JComponent component);

  @Override
  public boolean verify(JComponent component) {
    if (!validationCriteria(component)) {
      if (parent instanceof Validatable) {
        ((Validatable) parent).validationFailed();
      }

      component.setBackground(errorColor);
      component.setBorder(errorBorder);
      ToolTipManager.sharedInstance().setDismissDelay(60000);
      ToolTipManager.sharedInstance()
          .mouseMoved(new MouseEvent(component, 0, 0, 0, 0, 0, 0, false));
      return false;
    }

    component.setBackground(UIManager.getColor("TextField.background"));
    component.setBorder(UIManager.getBorder("TextField.border"));
    if (parent instanceof Validatable) {
      ((Validatable) parent).validationPassed();
    }

    ToolTipManager.sharedInstance().setInitialDelay(500);

    return true;
  }

  protected void setMessage(String text) {
    message.setText(text);
  }


  @Override
  public void keyPressed(KeyEvent event) {}

  @Override
  public void focusLost(FocusEvent event) {}

  @Override
  public void keyTyped(KeyEvent event) {}

  @Override
  public void keyReleased(KeyEvent event) {}

  @Override
  public void focusGained(FocusEvent event) {}

}
