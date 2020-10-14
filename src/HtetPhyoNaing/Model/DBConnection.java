package HtetPhyoNaing.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author GIC
 */
public class DBConnection {
    Connection connection;
    static final String DB_CONNECTION_STRING = "jdbc:postgresql://localhost:5432/foreword";
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "root";
    
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION_STRING, DB_USERNAME, DB_PASSWORD);
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Something Failed!", "Error", JOptionPane.ERROR_MESSAGE);
        } 
       
        return connection;
    }
}
