/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.UserController;
import model.User;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

/**
 *
 * @author anin7
 */
public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    UserController userController;
    
    public LoginView(){
        userController = new UserController();
        initComponents();
    }
    
    private void initComponents(){
        setTitle("LogistikApp - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 280);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0;
 
        JLabel lblTitle = new JLabel("Login Sistem", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 16, 0);
        mainPanel.add(lblTitle, gbc);
 
        gbc.insets = new Insets(4, 0, 4, 0);
 
        JLabel lblUser = new JLabel("Username:");
        gbc.gridy = 1;
        mainPanel.add(lblUser, gbc);
 
        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 2;
        mainPanel.add(txtUsername, gbc);
 
        JLabel lblPass = new JLabel("Password:");
        gbc.gridy = 3;
        mainPanel.add(lblPass, gbc);
 
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 4;
        mainPanel.add(txtPassword, gbc);
 
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(280, 32));
        gbc.gridy = 5;
        gbc.insets = new Insets(14, 0, 0, 0);
        mainPanel.add(btnLogin, gbc);
 
        btnLogin.addActionListener(e -> doLogin());
        txtPassword.addActionListener(e -> doLogin());
 
        add(mainPanel);
        setVisible(true);
    }
    
    private void doLogin(){
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
 
        try {
            User user = userController.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this,
                        "Login berhasil! Selamat datang, " + user.getUsername() + "!",
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new MenuView(user);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username atau password salah!",
                        "Login Gagal", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Validasi Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Koneksi database gagal!\nPastikan MySQL aktif dan konfigurasi benar.\n\nDetail: " + e.getMessage(),
                    "Error Database", JOptionPane.ERROR_MESSAGE);
        }
    }
}
