package matric;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface Provider {

    Statement st = createStatement();

    String createTable = "";

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
    
}
