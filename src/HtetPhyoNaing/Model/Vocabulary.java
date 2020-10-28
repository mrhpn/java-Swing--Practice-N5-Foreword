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
    private Boolean isFavorite;
    
    private final DBConnection DB_CONNECTION;
    Connection connection;
    
    public Vocabulary() {
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public Vocabulary(String name, String romaji, String jp_mm, String meaning, String lessonId, Boolean isFavorite) {
        this.name = name;
        this.romaji = romaji;
        this.jp_mm = jp_mm;
        this.meaning = meaning;
        this.lessonId = lessonId;
        this.isFavorite = isFavorite;
        
        DB_CONNECTION = new DBConnection();
        connection = DB_CONNECTION.getConnection();
    }
    
    public boolean insert() {
        int count = 0;
        
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO vocabularies (name, romaji, jp_mm, meaning, lesson_id, is_favorite) VALUES (?,?,?,?,?,?)")) {
            statement.setString(1, name);            
            statement.setString(2, romaji);
            statement.setString(3, jp_mm);
            statement.setString(4, meaning);
            statement.setInt(5, Integer.parseInt(lessonId));
            statement.setBoolean(6, isFavorite);

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
                "SELECT id AS No, name AS Vocabulary, romaji AS Romaji, jp_mm AS Pronouncation, meaning AS Meaning FROM vocabularies WHERE lesson_id = " + lessonId + " ORDER BY id " + order,
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
    
    public void repaintFavTable(JTable table, JLabel label, int lessonId) {
        ResultSet resultSet;
        String query;
        
        if (lessonId == 0)
            query = "SELECT id AS No, name AS Vocabulary, romaji AS Romaji, jp_mm AS Pronouncation, meaning AS Meaning FROM vocabularies WHERE is_favorite = true";
        else 
            query = "SELECT id AS No, name AS Vocabulary, romaji AS Romaji, jp_mm AS Pronouncation, meaning AS Meaning FROM vocabularies WHERE lesson_id = " + lessonId + " AND is_favorite = true";
        
        try(PreparedStatement statement = connection.prepareStatement(
                query,
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
    
    public void repaintTable(JTable table, JLabel label, String column, String value) {
        ResultSet resultSet;
        
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT id AS No, name AS Vocabulary, romaji AS Romaji, jp_mm AS Pronouncation, meaning AS Meaning FROM vocabularies WHERE " + column + " LIKE '%" + value + "%'",
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

    public boolean update(String id) {
        int count = 0;
        
        try (PreparedStatement statement = connection.prepareStatement(
                 "UPDATE vocabularies SET name=?, romaji=?, jp_mm=?, meaning=?, lesson_id=? WHERE id=" + id)) {
            statement.setString(1, name);
            statement.setString(2, romaji);
            statement.setString(3, jp_mm);
            statement.setString(4, meaning);
            statement.setInt(5, Integer.parseInt(lessonId));
            
            count = statement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return (count > 0);
    }

    public void delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM vocabularies WHERE id=" + id);) {
            statement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean isFavorite(Integer id) { 
        Boolean isFav = false;
        
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT is_favorite FROM vocabularies WHERE id=" + id);) {
            ResultSet isFavorite = statement.executeQuery();
            while(isFavorite.next()) {
                isFav = isFavorite.getBoolean("is_favorite");
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return isFav;
    }

    public boolean toggleFavorite(boolean isFavorite, int id) {
        int count = 0;
        
        try (PreparedStatement statement = connection.prepareStatement("UPDATE vocabularies SET is_favorite=? WHERE id=" + id);) {
            statement.setBoolean(1, !isFavorite);
            count = statement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return (count > 0);
    }
}
