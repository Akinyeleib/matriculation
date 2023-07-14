package matric;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface Provider {

    Statement st = createStatement();

    String createTableQuery = readFile("matric/table.txt");

    public static void main(String[] args) {
        System.out.println(createTableQuery);
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
