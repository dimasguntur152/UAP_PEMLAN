package org.example;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame
{
    private String role, username;
    // Menggunakan warna biru yang sama dengan halaman Login
    private final Color BLUE_THEME = new Color(0, 102, 204);

    public Dashboard(String role, String username)
    {
        this.role = role;
        this.username = username;
        setTitle("Dashboard - " + role);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int rows = role.equals("Mahasiswa") ? 4 : 3;
        JPanel panel = new JPanel(new GridLayout(rows, 1, 15, 15));
        panel.setBackground(Color.WHITE); // Mengubah background panel agar bersih
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JButton lihatBtn = new JButton("Lihat Daftar Barang");
        JButton riwayatBtn = new JButton("Riwayat");
        JButton laporBtn = new JButton("Laporkan Barang");
        JButton logoutBtn = new JButton("Logout");

        // Menerapkan style warna biru ke semua tombol
        styleButton(lihatBtn);
        styleButton(riwayatBtn);
        styleButton(laporBtn);
        styleButton(logoutBtn);

        lihatBtn.addActionListener(e -> pilihKategori());
        riwayatBtn.addActionListener(e -> new HistoryList(this, role, username).setVisible(true));
        laporBtn.addActionListener(e -> new ReportItemMahasiswa(this, username, null).setVisible(true));
        logoutBtn.addActionListener(e -> { new Login().setVisible(true); dispose(); });

        panel.add(lihatBtn);
        panel.add(riwayatBtn);
        if (role.equals("Mahasiswa")) panel.add(laporBtn);
        panel.add(logoutBtn);

        add(panel);
    }

    // Metode untuk mengatur tampilan tombol secara seragam
    private void styleButton(JButton button) {
        button.setBackground(BLUE_THEME);
        button.setForeground(Color.WHITE); // Teks putih agar kontras
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false); // Menghilangkan garis putus-putus saat diklik
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Mengubah kursor saat hover
    }

    private void pilihKategori()
    {
        String[] options = {"Barang Hilang", "Barang Ditemukan"};
        int res = JOptionPane.showOptionDialog(this, "Pilih Kategori", "Kategori",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (res == 0) openList("HILANG");
        else if (res == 1) openList("DITEMUKAN");
    }

    private void openList(String status)
    {
        if (role.equals("Mahasiswa")) new ItemListMahasiswa(this, status, username).setVisible(true);
        else new ItemListAdmin(this, status).setVisible(true);
    }
}