package matric.setup;

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.util.List;

import static matric.Components.*;
import static matric.setup.Provider.*;

import javax.swing.*;

public class Faculty extends JFrame implements ActionListener, ItemListener {

    JTextField fname;
    Statement st;
    
    public Faculty() {

        st = createStatement();

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        GridLayout grid = new GridLayout();
        panel.setLayout(grid);

        // Add Faculty
        panel.add(createLabel("Faculty Name"));
        fname = createTextField();
        panel.add(fname);
        panel.add(createButton("Add Faculty", this));

        // Remove Faculty
        panel.add(createLabel("Delete Faculty"));
        // faculty -> dropdown
        String [] faculties = {"Science", "Engineering", "Environmental", "Education"};
        List <String> list = fetchFaculties(st);
        JComboBox<String> faculty = createComboBox(list, this);
        panel.add(faculty);
        panel.add(createButton("Add Faculty", this));

        grid.setColumns(2);
        grid.setRows(7);
        grid.setHgap(5);
        grid.setVgap(10);
        
        add(panel);

        setTitle("Register Student");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clicked = e.getActionCommand();
        if (clicked.equals("Add Faculty")) {
            String query = String.format("INSERT INTO Faculty (name) VALUES ('%S')", fname.getText());
            executeQuery(query, st);
            System.out.println("Faculty Added");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if (e.getSource() instanceof JComboBox) {

            JComboBox<String> box = (JComboBox<String>) e.getSource();
            int index = box.getSelectedIndex();
            index++;

            // if (box == faculty) {
            //     List <String> list = fetchDepartments(index);
            //     reloadComboBoxItems(department, list);
            //     System.out.println("Faculty selected is: " + index);
            // }
            // else if (box == department) {
            //     System.out.println("Department selected is: " + index);
            // }

        }

    }
    
}
