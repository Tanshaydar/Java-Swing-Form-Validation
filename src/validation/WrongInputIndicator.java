package validation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;

public class WrongInputIndicator extends JToolTip {

  private JLabel wrongInputIndicatorLabel;
  private JLabel wrongInputIndicatorImage;
  private JPanel wrongInputIndicatorPanel;

  public WrongInputIndicator(JComponent component) {
    super();
    setComponent(component);

    try {
      wrongInputIndicatorImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/images/unverified.png"))));
    } catch (IOException ex) {
      System.err.println(ex.toString());
    }
    wrongInputIndicatorLabel = new JLabel();
    wrongInputIndicatorPanel = new JPanel(new FlowLayout());
    wrongInputIndicatorPanel.setBackground(Color.YELLOW);
    wrongInputIndicatorPanel.setForeground(Color.RED);

    wrongInputIndicatorPanel.add(wrongInputIndicatorImage);
    wrongInputIndicatorPanel.add(wrongInputIndicatorLabel);
    setLayout(new BorderLayout());
    add(wrongInputIndicatorPanel);
  }

  @Override
  public Dimension getPreferredSize() {
    return wrongInputIndicatorPanel.getPreferredSize();
  }

  @Override
  public void setTipText(String tipText) {
    if (tipText != null && !tipText.isEmpty()) {
      wrongInputIndicatorLabel.setText(tipText);
    } else {
      super.setTipText(tipText);
    }
  }

  @Override
  public Point getToolTipLocation(MouseEvent event) {
    return new Point(0, 0);
  }
}
