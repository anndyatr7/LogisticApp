/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;


/**
 *
 * @author anin7
 */
public class DatabaseConnection {
    private static String nama_db = "transportasi_db";
    private static String url_db = "jdbc:mysql://localhost:3306/transportasi_db";
    private static String username_db = "root";
    private static String password_db = "";

    private static Connection connection = null;
    
    public static Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url_db, username_db, password_db);
            } catch(ClassNotFoundException e){
                throw new SQLException("MySQL Driver tidak ditemukan: " + e.getMessage());
            }
        }
        return connection;
    }
    
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
                connection = null;
            } catch(SQLException e){
                System.err.println("Error menutup koneksi: " + e.getMessage());
            }
        }
    }
}
