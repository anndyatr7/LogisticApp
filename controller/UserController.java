/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseConnection;
import model.User;
import java.sql.*;

/**
 *
 * @author anin7
 */
public class UserController {
    public User login(String username, String password) throws SQLException{
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }
        if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username.trim());
        stmt.setString(2, password.trim());
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
        } else{
            return null;
        }
    }
}
