package matric.user;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import matric.Components;

public class SignUp extends JFrame implements ActionListener, ItemListener {
    
    public SignUp() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        GridLayout grid = new GridLayout();
        grid.setColumns(2);
        panel.setLayout(grid);

        panel.add(Components.createLabel("First Name"));
        JTextField fname = Components.createTextField();
        panel.add(fname);
        
        panel.add(Components.createLabel("Last Name"));
        JTextField lname = Components.createTextField();
        panel.add(lname);

        panel.add(Components.createLabel("Faculty"));
        // faculty -> dropdown
        String [] faculties = {"Science", "Engineering", "Environmental", "Education"};
        var faculty = Components.createComboBox(faculties, this);
        panel.add(faculty);

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
        System.out.println("Action Event");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Item Event");
    }

    public static void main(String[] args) {
        new SignUp();
    }

}
