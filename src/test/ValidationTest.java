package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import validation.DoubleValidator;
import validation.IntegerValidator;
import validation.NotNullValidator;
import validation.StringLengthValidator;
import validation.ValidatablePanel;
import validation.WrongInputIndicator;

public class ValidationTest extends JFrame {

  private ValidatablePanel contentPane;
  private JTextField textFieldLength;
  private JTextField textFieldInteger;
  private JTextField textFieldDouble;
  private JTextField textFieldNotNull;
  private JButton buttonSave;
  private JButton buttonUpdate;
  private JButton buttonCancel;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ValidationTest frame = new ValidationTest();
          frame.pack();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public ValidationTest() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new ValidatablePanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new MigLayout("",
        "[100px:100px:100px][100px:100px:100px][100px:100px:100px]",
        "[25px][25px][25px][25px][25px]"));

    JLabel lblLengthChecker = new JLabel("Length Checker");
    lblLengthChecker.setFont(new Font("Tahoma", Font.BOLD, 11));
    contentPane.add(lblLengthChecker, "cell 0 0,alignx left,growy");

    textFieldLength = new JTextField() {
      public JToolTip createToolTip() {
        return (new WrongInputIndicator(contentPane));
      }
    };
    contentPane.add(textFieldLength, "cell 1 0 2 1,grow");
    textFieldLength.setColumns(10);

    JLabel lblIntegerChecker = new JLabel("Integer Checker");
    lblIntegerChecker.setFont(new Font("Tahoma", Font.BOLD, 11));
    contentPane.add(lblIntegerChecker, "cell 0 1,alignx left,growy");

    textFieldInteger = new JTextField() {
      public JToolTip createToolTip() {
        return (new WrongInputIndicator(contentPane));
      }
    };
    contentPane.add(textFieldInteger, "cell 1 1 2 1,grow");
    textFieldInteger.setColumns(10);

    JLabel lblDoubleChecker = new JLabel("Double Checker");
    lblDoubleChecker.setFont(new Font("Tahoma", Font.BOLD, 11));
    contentPane.add(lblDoubleChecker, "cell 0 2,alignx left,growy");

    textFieldDouble = new JTextField() {
      public JToolTip createToolTip() {
        return (new WrongInputIndicator(contentPane));
      }
    };
    contentPane.add(textFieldDouble, "cell 1 2 2 1,grow");
    textFieldDouble.setColumns(10);

    JLabel lblNotNullChecker = new JLabel("Not Null Checker");
    lblNotNullChecker.setFont(new Font("Tahoma", Font.BOLD, 11));
    contentPane.add(lblNotNullChecker, "cell 0 3,alignx left,growy");

    textFieldNotNull = new JTextField() {
      public JToolTip createToolTip() {
        return (new WrongInputIndicator(contentPane));
      }
    };
    contentPane.add(textFieldNotNull, "cell 1 3 2 1,grow");
    textFieldNotNull.setColumns(10);

    buttonCancel = new JButton("Cancel");
    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {}
    });
    contentPane.add(buttonCancel, "cell 0 4,grow");

    buttonUpdate = new JButton("Update");
    contentPane.add(buttonUpdate, "cell 1 4,grow");

    buttonSave = new JButton("Save");
    contentPane.add(buttonSave, "cell 2 4,grow");

    setValidators();
  }

  private void setValidators() {
    textFieldLength.setInputVerifier(new StringLengthValidator(contentPane, textFieldLength,
        "You can enter up to 20 character!", 20));
    textFieldInteger.setInputVerifier(new IntegerValidator(contentPane, textFieldInteger,
        "You can only enter integer values in this field. You can leave this field empty.", true));
    textFieldDouble.setInputVerifier(new DoubleValidator(contentPane, textFieldDouble,
        "You can only enter values between 0.2 and 6.75 and you cannot leave this field empty!",
        false, 0.2, 6.75));
    textFieldNotNull.setInputVerifier(new NotNullValidator(contentPane, textFieldNotNull,
        "You cannot leave this field empty!"));
    
    contentPane.setButtonsToBeAffectedAfterValidation(buttonSave, buttonUpdate);
  }

}
