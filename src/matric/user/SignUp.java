package matric.user;

import static matric.Components.createButton;
import static matric.Components.createComboBox;
import static matric.Components.createLabel;
import static matric.Components.createTextField;
import static matric.Components.reloadComboBoxItems;
import static matric.setup.Provider.createStatement;
import static matric.setup.Provider.fetchDepartments;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class SignUp extends JFrame implements ActionListener, ItemListener {
    
    JComboBox<String> faculty, department;
    Statement st;

    public SignUp() {

        st = createStatement();

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        GridLayout grid = new GridLayout();
        panel.setLayout(grid);

        panel.add(createLabel("First Name"));
        JTextField fname = createTextField();
        panel.add(fname);
        
        panel.add(createLabel("Last Name"));
        JTextField lname = createTextField();
        panel.add(lname);

        panel.add(createLabel("Faculty"));
        // faculty -> dropdown
        String [] faculties = {"Science", "Engineering", "Environmental", "Education"};
        List <String> list = new ArrayList<>();
        faculty = createComboBox(list, this);
        panel.add(faculty);

        panel.add(createLabel("Department"));
        // department -> dropdown
        String [] departments = {"Physics", "CVE", "Urban & Regional Planning", "Childhood Education"};
        department = createComboBox(list, this);
        panel.add(department);
        
        grid.setColumns(2);
        grid.setRows(7);
        grid.setHgap(5);
        grid.setVgap(10);

        panel.add(createButton("Reset", this));
        panel.add(createButton("Submit", this));
        
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
        
        if (e.getSource() instanceof JComboBox) {

            JComboBox<String> box = (JComboBox<String>) e.getSource();
            int index = box.getSelectedIndex();
            index++;

            if (box == faculty) {
                List <String> list = fetchDepartments(index, st);
                reloadComboBoxItems(department, list);
                System.out.println("Faculty selected is: " + index);
            }
            else if (box == department) {
                System.out.println("Department selected is: " + index);
            }

        }
        
    }

    public static void main(String[] args) {
        new SignUp();
    }

}
