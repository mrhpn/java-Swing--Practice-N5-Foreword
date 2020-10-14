/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtetPhyoNaing.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author GIC
 */
public class Lesson {
    private String name;
    
    private final DBConnection DB_CONNECTION;
    Connection connection;
    
    public Lesson() {
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public Lesson(String name) {
        this.name = name;
        
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public void insert(JTable tableLessons) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO lessons (name) VALUES (?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
            
            repaintTable(tableLessons);
        } 
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void repaintTable(JTable table) {
        ResultSet resultSet;
        
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM lessons ORDER BY id ASC")) {
            resultSet = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
        catch(SQLException e) {
           System.out.println(e.getMessage());
        }
    }
    
}
