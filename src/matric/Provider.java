package matric;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface Provider {

    Statement st = createStatement();

    String createTableQuery = readFile("matric/table.txt");

    public static void createTables() {
        String [] splitted = createTableQuery.split(";\n");
        for (int i = splitted.length - 1; i >= 0; i--) {
            executeQuery(splitted[i]);
        }
    }

    static boolean executeQuery(String query) {
        try {
            return st.execute(query);
        } catch (SQLException e) {
            System.out.println("Error: Occured");
        }
        return false;
    }

    static Statement createStatement() {
        String user = "root", password = "root",
        url = "jdbc:mysql://localhost:3306/matriculation";
        Statement st = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Driver loaded");
            st = conn.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            System.out.println("Error occured: " + e.getMessage());
        }
        return st;
    }

    static List<String> fetchDepartments(int ID) {
        String query = "SELECT name from DEPARTMENT WHERE FacultyID = " + ID;
        List <String> values = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                values.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error occured: " + e.getMessage());
        }
        return values;
    }

    static List<String> fetchFaculties() {
        String query = "SELECT name from FACULTY";
        List <String> values = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                values.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error occured: " + e.getMessage());
        }
        return values;
    }

    static String readFile(String file) {
        String readLines = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                readLines = readLines != null ? readLines += line : line;
                readLines +="\n";
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FNF-Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOError: " + e.getMessage());
        }
        return readLines;
    }
    
}
