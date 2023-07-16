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
    JComboBox<String> faculty;
    
    public Faculty() {

        st = createStatement();

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        GridLayout grid = new GridLayout();
        grid.setColumns(2);
        grid.setRows(2);
        grid.setHgap(5);
        grid.setVgap(10);
        panel.setLayout(grid);

        // Add Faculty
        fname = createTextField();
        panel.add(fname);
        panel.add(createButton("Add Faculty", this));

        // Remove Faculty
        List <String> list = fetchFaculties(st);
        faculty = createComboBox(list, this);
        panel.add(faculty);
        panel.add(createButton("Delete Faculty", this));

        
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
        if (clicked.equals("Add Faculty")) {
            String query = String.format("INSERT INTO Faculty (name) VALUES ('%S')", fname.getText());
            executeQuery(query, st);
            System.out.format("Faculty %s Added\n", fname.getText());
            loadComboBox(fetchFaculties(st), faculty);
        } else if (clicked.equals("Delete Faculty")) {
            String query = String.format("DELETE FROM Faculty WHERE name = '%S'", faculty.getSelectedItem());
            executeQuery(query, st);
            System.out.format("Faculty %s Added\n", fname.getText());
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
