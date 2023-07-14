package matric;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface Components {
    
    static Color fgColor = Color.YELLOW, bgColor = Color.BLACK;
    static Font font = new Font("Ink Free", Font.ITALIC, 20);

    public static JButton createButton(String txt, ActionListener listener) {

        JButton btn = new JButton(txt);
        btn.setForeground(fgColor);
        btn.setBackground(bgColor);
        btn.setFont(font);
        btn.setFocusable(false);
        btn.addActionListener(listener);

        return btn;
    }

    public static JTextField createTextField() {
        JTextField txtField = new JTextField(50);
        txtField.setFont(font);
        txtField.setForeground(fgColor);
        txtField.setBackground(bgColor);
        txtField.setCaretColor(Color.CYAN);

        return txtField;
    }

    public static JPasswordField createPasswordField() {
        JPasswordField txtField = new JPasswordField(50);
        txtField.setFont(font);
        txtField.setForeground(fgColor);
        txtField.setBackground(bgColor);
        txtField.setCaretColor(Color.CYAN);

        return txtField;
    }

    public static JLabel createLabel(String txt) {
        JLabel label = new JLabel(txt);
        label.setFont(font);
        label.setForeground(fgColor);
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    } 

}
