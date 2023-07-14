package matric.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import matric.Components;

public class SignUp extends JFrame implements ActionListener {
    
    public SignUp() {

        JPanel panel = new JPanel();

        panel.add(Components.createLabel("First Name"));
        JTextField fname = Components.createTextField();
        panel.add(fname);
        
        panel.add(Components.createLabel("Last Name"));
        JTextField lname = Components.createTextField();
        panel.add(lname);

        // faculty -> dropdown
        // department -> dropdown

        panel.add(Components.createButton("Reset", this));
        panel.add(Components.createButton("Submit", this));
        
        add(panel);

        setTitle("Register Student");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
