/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.DatabaseConnection;
import model.Kendaraan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author anin7
 */
public class KendaraanController {
    public List<Kendaraan> getAllKendaraan() throws SQLException{
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan ORDER BY id";
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
            list.add(new Kendaraan(
                rs.getInt("id"),
                rs.getString("plat_nomor"),
                rs.getString("jenis"),
                rs.getString("merk")));
        }
        return list;
    }
    
    public List<Kendaraan> searchKendaraan(String keyword) throws SQLException{
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan WHERE plat_nomor LIKE ? OR merk LIKE ? ORDER BY id";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        String kw = "%" + keyword + "%";
        stmt.setString(1, kw);
        stmt.setString(2, kw);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            list.add(new Kendaraan(
                rs.getInt("id"),
                rs.getString("plat_nomor"),
                rs.getString("jenis"),
                rs.getString("merk")));
        }
        return list;
    }
    
    public void tambahKendaraan(Kendaraan k) throws SQLException{
        if(k.getPlatNomor().trim().isEmpty() || k.getJenis().trim().isEmpty() || k.getMerk().trim().isEmpty()){
            throw new IllegalArgumentException("Semua field harus diisi!");
        }
        String sql = "INSERT INTO kendaraan (plat_nomor, jenis, merk) VALUES (?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, k.getPlatNomor().trim());
        stmt.setString(2, k.getJenis().trim());
        stmt.setString(3, k.getMerk().trim());
        stmt.executeUpdate();
    }
    
    public void editKendaraan(Kendaraan k) throws SQLException{
        if(k.getPlatNomor().trim().isEmpty() || k.getJenis().trim().isEmpty() || k.getMerk().trim().isEmpty()){
            throw new IllegalArgumentException("Semua field harus diisi");
        }
        String sql = "UPDATE kendaraan SET plat_nomor=?, jenis=?, merk=? WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, k.getPlatNomor().trim());
        stmt.setString(2, k.getJenis().trim());
        stmt.setString(3, k.getMerk().trim());
        stmt.setInt(4, k.getId());
        stmt.executeUpdate();
    }
    
    public void hapusKendaraan(int id) throws SQLException{
        String sql = "DELETE FROM kendaraan WHERE id=?";
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
