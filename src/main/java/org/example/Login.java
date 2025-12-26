package org.example;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton rbMahasiswa, rbAdmin;

    // Definisi warna biru agar konsisten
    private final Color BLUE_THEME = new Color(0, 102, 204);

    public Login() {
        setTitle("Login - Sistem Lost & Found Kampus");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Teks Judul 1 - Diubah ke Warna Biru
        JLabel title1 = new JLabel("Selamat Datang di", SwingConstants.CENTER);
        title1.setFont(new Font("SansSerif", Font.BOLD, 22));
        title1.setForeground(BLUE_THEME); // Set warna biru
        gbc.gridy = 0; gbc.gridwidth = 2; panel.add(title1, gbc);

        // Teks Judul 2 - Diubah ke Warna Biru
        JLabel title2 = new JLabel("Sistem Lost & Found Kampus", SwingConstants.CENTER);
        title2.setFont(new Font("SansSerif", Font.BOLD, 22));
        title2.setForeground(BLUE_THEME); // Set warna biru
        gbc.gridy = 1; panel.add(title2, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(25, 10, 5, 10);
        panel.add(new JLabel("Masukkan Username :"), gbc);

        gbc.gridy = 3; gbc.insets = new Insets(0, 10, 15, 10);
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(350, 30));
        usernameField.setBackground(new Color(215, 215, 215));
        panel.add(usernameField, gbc);

        gbc.gridy = 4; gbc.insets = new Insets(5, 10, 5, 10);
        panel.add(new JLabel("Masukkan Password :"), gbc);

        gbc.gridy = 5; gbc.insets = new Insets(0, 10, 15, 10);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 30));
        passwordField.setBackground(new Color(215, 215, 215));
        panel.add(passwordField, gbc);

        rbMahasiswa = new JRadioButton("Mahasiswa", true);
        rbAdmin = new JRadioButton("Admin");
        ButtonGroup group = new ButtonGroup();
        group.add(rbMahasiswa); group.add(rbAdmin);
        JPanel rolePanel = new JPanel();
        rolePanel.setBackground(Color.WHITE);
        rolePanel.add(rbMahasiswa); rolePanel.add(rbAdmin);
        gbc.gridy = 6; panel.add(rolePanel, gbc);

        // Tombol Login - Diubah ke Warna Biru dengan teks putih
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(120, 35));
        loginBtn.setBackground(BLUE_THEME); // Warna background biru
        loginBtn.setForeground(Color.WHITE); // Warna teks putih agar kontras
        loginBtn.setFocusPainted(false); // Menghilangkan garis fokus agar lebih rapi
        loginBtn.addActionListener(e -> login());

        gbc.gridy = 7; gbc.fill = GridBagConstraints.NONE;
        panel.add(loginBtn, gbc);

        add(panel);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = rbMahasiswa.isSelected() ? "Mahasiswa" : "Admin";

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "username dan password tidak boleh kosong!");
            return;
        }

        boolean valid = false;

        if (role.equals("Admin")) {
            if ((username.equals("Dimass") && password.equals("Admin152")) || (username.equals("Nasywah") && password.equals("Admin121"))) {
                valid = true;
            }
        } else {
            if ((username.equals("Dimas Guntur Prasetya Sumoro") && password.equals("2024152")) || (username.equals("Nasywah Dzufairah Azahra") && password.equals("2024121"))) {
                valid = true;
            }
        }

        if (valid) {
            new Dashboard(role, username).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!");
        }
    }
}