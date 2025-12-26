package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class HistoryList extends JFrame
{
    // Warna biru tema yang konsisten
    private final Color BLUE_THEME = new Color(0, 102, 204);

    public HistoryList(JFrame parent, String role, String user)
    {
        setTitle("Riwayat - " + role);
        setSize(900, 500);
        setLocationRelativeTo(parent);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nama", "Status", "Pelapor", "Tanggal"}, 0);

        // Membaca data dari file
        String file = role.equals("Admin") ? "data/history.txt" : "data/items.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                Item i = Item.fromFile(line);
                // Filter: Jika mahasiswa, hanya tampilkan milik sendiri
                if (role.equals("Mahasiswa") && !i.getReporter().equals(user)) continue;
                model.addRow(new Object[]{i.getNama(), i.getStatus(), i.getReporter(), i.getTanggal()});
            }
        }
        catch (Exception e) {
            System.err.println("Gagal membaca history: " + e.getMessage());
        }

        // Setup Tabel
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Setup Tombol Tutup
        JButton back = new JButton("Tutup");
        back.setBackground(BLUE_THEME);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("SansSerif", Font.BOLD, 13));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setPreferredSize(new Dimension(100, 35));
        back.addActionListener(e -> dispose());

        // Panel bawah untuk menampung tombol agar lebih rapi
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.add(back);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}