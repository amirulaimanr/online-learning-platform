/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.repository.UserRepository;

/**
 *
 * @author lolip
 */
public class CategoryDao {

    private DatabaseConnection dbConnection;

    public CategoryDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public CategoryDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Optional<Category> find(int id) throws SQLException{
        String sql = "SELECT * FROM categories WHERE id=?";
        int cat_id = 0;
        String name = "",description="";
        
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            cat_id = resultSet.getInt("id");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        
        return Optional.of(new Category(cat_id,name,description));
    }
    
    public List<Category> listAll() throws SQLException{
        List<Category> listCat = new ArrayList();
        String sql = "SELECT * FROM categories ORDER BY id ASC";
        
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            
            Category cat = new Category(id,name,description);
            listCat.add(cat);
        }
        
        return listCat;
    }
    
    public void store(String name, String description) throws SQLException {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);
        statement.executeUpdate();
    }
    
    public boolean update(Category cat) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE categories SET name=?,description=? WHERE id=?";
        
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, cat.getName());
        statement.setString(2, cat.getDescription());
        statement.setInt(3, cat.getId());
        rowupdate = statement.executeUpdate() > 0;
        
        return rowupdate;
    }
    
    public boolean delete(Category cat) throws SQLException {
        boolean rowdelete = false;
        String sql = "DELETE FROM categories WHERE id=?";
        
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, cat.getId());
        rowdelete = statement.executeUpdate() > 0;
        
        return rowdelete;
    }

}
