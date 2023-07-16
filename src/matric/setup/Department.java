package matric.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Statement;
import java.util.List;

import static matric.Components.*;
import static matric.setup.Provider.*;

public class Department extends JFrame implements ActionListener, ItemListener {

    JTextField name;
    Statement st;
    JComboBox<String> faculty;

    public Department() {

        st = createStatement();

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        GridLayout grid = new GridLayout();
        grid.setColumns(2);
        grid.setRows(2);
        grid.setHgap(5);
        grid.setVgap(10);
        panel.setLayout(grid);

        // Select Faculty
        name = createTextField();
        List <String> list = fetchFaculties(st);
        faculty = createComboBox(list, this);
        panel.add(faculty);
        panel.add(name);
        panel.add(createButton("Add Department", this));

        // Remove Department
//        faculty = createComboBox(list, this);
//        panel.add(faculty);
//        panel.add(createButton("Delete Department", this));

        
        add(panel);

        setTitle("Faculty");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clicked = e.getActionCommand();

        if (clicked.equals("Add Department")) {
            int ID = getFacultyID(st, (String) faculty.getSelectedItem());
            if (ID != -1) {
                String query = String.format("INSERT INTO Department (name, facultyID) VALUES ('%S', %d)", name.getText(), ID);
                System.out.println("Query: " + query);
                executeQuery(query, st);
                System.out.format("Department %s Added\n", name.getText());
                loadComboBox(fetchFaculties(st), faculty);
            }
        } else if (clicked.equals("Delete Department")) {

            String query = String.format("DELETE FROM Faculty WHERE name = '%S'", faculty.getSelectedItem());
            executeQuery(query, st);
            System.out.format("Faculty %s Added\n", name.getText());
            loadComboBox(fetchFaculties(st), faculty);
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
