/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.User;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 * @author anin7
 */
public class MenuView extends JFrame {
    private User loggedUser;
    
    public MenuView(User user){
        this.loggedUser = user;
        initComponents();
    }
    
    private void initComponents(){
        setTitle("LogistikApp - Menu Utama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0;
 
        JLabel lblWelcome = new JLabel("Selamat Datang, " + loggedUser.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 16, 0);
        mainPanel.add(lblWelcome, gbc);
 
        gbc.insets = new Insets(6, 0, 6, 0);
 
        JLabel lblPilih = new JLabel("Pilih menu kelola data:", SwingConstants.CENTER);
        gbc.gridy = 1;
        mainPanel.add(lblPilih, gbc);
 
        JButton btnKendaraan = new JButton("Data Kendaraan");
        btnKendaraan.setPreferredSize(new Dimension(240, 32));
        gbc.gridy = 2;
        mainPanel.add(btnKendaraan, gbc);
 
        JButton btnSopir = new JButton("Data Sopir");
        btnSopir.setPreferredSize(new Dimension(240, 32));
        gbc.gridy = 3;
        mainPanel.add(btnSopir, gbc);
 
        JButton btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(240, 32));
        gbc.gridy = 4;
        mainPanel.add(btnLogout, gbc);
 
        btnKendaraan.addActionListener(e -> {
            new KendaraanView(loggedUser);
            this.setVisible(false);
        });
 
        btnSopir.addActionListener(e -> {
            new SopirView(loggedUser);
            this.setVisible(false);
        });
 
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin logout?",
                    "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginView();
            }
        });
 
        add(mainPanel);
        setVisible(true);
    }
}
