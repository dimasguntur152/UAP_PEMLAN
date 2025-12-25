package org.example;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame
{
    private String role, username;

    public Dashboard(String role, String username)
    {
        this.role = role;
        this.username = username;
        setTitle("Dashboard");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int rows = role.equals("Mahasiswa") ? 4 : 3;
        JPanel panel = new JPanel(new GridLayout(rows, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton lihatBtn = new JButton("Lihat Daftar Barang");
        JButton riwayatBtn = new JButton("Riwayat");
        JButton laporBtn = new JButton("Laporkan Barang");
        JButton logoutBtn = new JButton("Logout");

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

    private void pilihKategori()
    {
        String[] options = {"Barang Hilang", "Barang Ditemukan"};
        int res = JOptionPane.showOptionDialog(this, "Pilih Kategori", "Kategori", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (res == 0) openList("HILANG");
        else if (res == 1) openList("DITEMUKAN");
    }

    private void openList(String status)
    {
        if (role.equals("Mahasiswa")) new ItemListMahasiswa(this, status, username).setVisible(true);
        else new ItemListAdmin(this, status).setVisible(true);
    }
}