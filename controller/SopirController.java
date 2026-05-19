/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseConnection;
import model.Sopir;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anin7
 */
public class SopirController {
    public List<Sopir> getAllSopir() throws SQLException{
        List<Sopir> list = new ArrayList<>();
        String sql = "SELECT * FROM sopir ORDER BY id";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            list.add(new Sopir(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("no_sim"),
                rs.getString("no_hp")));
        }
        return list;
    }
    
    public List<Sopir> searchSopir(String keyword) throws SQLException{
        List<Sopir> list = new ArrayList<>();
        String sql = "SELECT * FROM sopir WHERE nama LIKE ? OR no_sim LIKE ? ORDER BY id";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        String kw = "%" + keyword + "%";
        stmt.setString(1, kw);
        stmt.setString(2, kw);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            list.add(new Sopir(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("no_sim"),
                rs.getString("no_hp")));
        }
        return list;
    }
    
    public void tambahSopir(Sopir s) throws SQLException{
        if(s.getNama().trim().isEmpty() || s.getNoSim().trim().isEmpty() || s.getNoHp().trim().isEmpty()){
            throw new IllegalArgumentException("Semua field harus diisi!");
        }
        String sql = "INSERT INTO sopir (nama, no_sim, no_hp) VALUES (?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getNama().trim());
        stmt.setString(2, s.getNoSim().trim());
        stmt.setString(3, s.getNoHp().trim());
        stmt.executeUpdate();
    }
    
    public void editSopir(Sopir s) throws SQLException{
        if(s.getNama().trim().isEmpty() || s.getNoSim().trim().isEmpty() || s.getNoHp().trim().isEmpty()){
            throw new IllegalArgumentException("Semua field harus diisi!");
        }
        String sql = "UPDATE sopir SET nama=?, no_sim=?, no_hp=? WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getNama().trim());
        stmt.setString(2, s.getNoSim().trim());
        stmt.setString(3, s.getNoHp().trim());
        stmt.setInt(4, s.getId());
        stmt.executeUpdate();
    }
    
    public void hapusSopir(int id) throws SQLException{
        String sql = "DELETE FROM sopir WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
