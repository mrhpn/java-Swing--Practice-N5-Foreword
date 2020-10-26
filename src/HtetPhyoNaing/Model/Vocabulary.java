/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtetPhyoNaing.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author GIC
 */
public class Vocabulary {
    private String name;    
    private String romaji;
    private String jp_mm;
    private String meaning;    
    private String lessonId;

    
    private final DBConnection DB_CONNECTION;
    Connection connection;
    
    public Vocabulary() {
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public Vocabulary(String name, String romaji, String jp_mm, String meaning, String lessonId) {
        this.name = name;
        this.romaji = romaji;
        this.jp_mm = jp_mm;
        this.meaning = meaning;
        this.lessonId = lessonId;
        
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public boolean insert() {
        int count = 0;
        
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO vocabularies (name,romaji,jp_mm,meaning,lesson_id) VALUES (?,?,?,?," + lessonId + ")")) {
            statement.setString(1, name);            
            statement.setString(2, romaji);
            statement.setString(3, jp_mm);
            statement.setString(4, meaning);

            count = statement.executeUpdate();
        } 
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return (count > 0);
    }
    
    public void repaintTable(JTable table, JLabel label, String order, int lessonId) {
        ResultSet resultSet;
        
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT name, romaji, jp_mm, meaning FROM vocabularies WHERE lesson_id = " + lessonId + " ORDER BY id " + order,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            resultSet = statement.executeQuery();
            
            // repaint table
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            // total rows
            resultSet.last();
            label.setText("Total Rows: " + resultSet.getRow());
        }
        catch(SQLException e) {
           System.out.println(e.getMessage());
        }
    }
    
    public void repaintTable(JTable table, JLabel label, String column, String value, int lessonId) {
        ResultSet resultSet;
        
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT name, romaji, jp_mm, meaning FROM vocabularies WHERE lesson_id = " + lessonId + " AND " + column + " LIKE '%" + value + "%'",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            resultSet = statement.executeQuery();
            
            // repaint table
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            
            // total rows
            resultSet.last();
            label.setText("Total Rows: " + resultSet.getRow());
        }
        catch(SQLException e) {
           System.out.println(e.getMessage());
        }
    }
}
