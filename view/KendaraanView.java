/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.KendaraanController;
import model.Kendaraan;
import model.User;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anin7
 */
public class KendaraanView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtCari;
    private KendaraanController controller;
    private User loggedUser;
 
    public KendaraanView(User user) {
        this.loggedUser = user;
        this.controller = new KendaraanController();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Kelola Data Kendaraan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 480);
        setLocationRelativeTo(null);
 
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
 
        JPanel topPanel = new JPanel(new BorderLayout(8, 0));
 
        JLabel lblTitle = new JLabel("Tabel Data Kendaraan");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
 
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        searchPanel.add(new JLabel("Cari:"));
        txtCari = new JTextField(16);
        searchPanel.add(txtCari);
        JButton btnCari = new JButton("Cari Data");
        searchPanel.add(btnCari);
 
        topPanel.add(lblTitle, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
 
        // tabel
        String[] columns = {"ID", "Plat Nomor", "Jenis Kendaraan", "Merk"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
 
        JScrollPane scrollPane = new JScrollPane(table);
 
        // bottm button
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBorder(new EmptyBorder(6, 0, 0, 0));
 
        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnKembali = new JButton("Kembali ke Menu");
 
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnKembali);
 
        // action listeners
        btnCari.addActionListener(e -> doSearch());
        txtCari.addActionListener(e -> doSearch());
        btnTambah.addActionListener(e -> showFormTambah());
        btnEdit.addActionListener(e -> showFormEdit());
        btnHapus.addActionListener(e -> doHapus());
        btnKembali.addActionListener(e -> {
            this.dispose();
            new MenuView(loggedUser).setVisible(true);
        });
 
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
 
        add(mainPanel);
        setVisible(true);
    }
    
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setOpaque(true);
        return btn;
    }
    
    private void loadData() {
         try {
            refreshTable(controller.getAllKendaraan());
        } catch (SQLException e) {
            showDBError(e);
        }
    }
 
    private void doSearch() {
        String keyword = txtCari.getText().trim();
        try {
            List<Kendaraan> list = keyword.isEmpty()
                    ? controller.getAllKendaraan()
                    : controller.searchKendaraan(keyword);
            refreshTable(list);
        } catch (SQLException e) {
            showDBError(e);
        }
    }
    
    private void refreshTable(List<Kendaraan> list) {
        tableModel.setRowCount(0);
        for (Kendaraan k : list) {
            tableModel.addRow(new Object[]{k.getId(), k.getPlatNomor(), k.getJenis(), k.getMerk()});
        }
    }
    
    private void showFormTambah() {
        JTextField txtPlat = new JTextField(15);
        JTextField cmbJenis = new JTextField(18);
        JTextField txtMerk = new JTextField(15);
 
        JPanel panel = buildFormPanel(
                new String[]{"Plat Nomor:", "Jenis Kendaraan:", "Merk:"},
                new JComponent[]{txtPlat, cmbJenis, txtMerk});
 
        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Kendaraan",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
 
        if (result == JOptionPane.OK_OPTION) {
            try {
                controller.tambahKendaraan(new Kendaraan(
                        txtPlat.getText(),
                        cmbJenis.getText(),
                        txtMerk.getText()));
                loadData();
                JOptionPane.showMessageDialog(this, "Data kendaraan berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showFormEdit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        JTextField txtPlat = new JTextField((String) tableModel.getValueAt(selectedRow, 1), 15);
        JTextField cmbJenis = new JTextField((String) tableModel.getValueAt(selectedRow, 2), 15);
        JTextField txtMerk = new JTextField((String) tableModel.getValueAt(selectedRow, 3), 15);
 
        JPanel panel = buildFormPanel(
                new String[]{"Plat Nomor:", "Jenis Kendaraan:", "Merk:"},
                new JComponent[]{txtPlat, cmbJenis, txtMerk});
 
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Kendaraan",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
 
        if (result == JOptionPane.OK_OPTION) {
            try {
                controller.editKendaraan(new Kendaraan(id, txtPlat.getText(),
                        cmbJenis.getText(), txtMerk.getText()));
                loadData();
                JOptionPane.showMessageDialog(this, "Data kendaraan berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void doHapus() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String plat = (String) tableModel.getValueAt(selectedRow, 1);
 
        int confirm = JOptionPane.showConfirmDialog(this,
                "Hapus kendaraan dengan plat \"" + plat + "\"?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
 
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.hapusKendaraan(id);
                loadData();
                JOptionPane.showMessageDialog(this, "Data kendaraan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                showDBError(e);
            }
        }
    }
    
    private JPanel buildFormPanel(String[] labels, JComponent[] fields) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            panel.add(fields[i], gbc);
        }
        return panel;
    }
 
    private void showDBError(SQLException e) {
        JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
