package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListMahasiswa extends JFrame
{
    private DefaultTableModel model;
    private JTable table;
    private String status, user;

    // Warna biru tema yang konsisten
    private final Color BLUE_THEME = new Color(0, 102, 204);

    public ItemListMahasiswa(JFrame parent, String status, String user)
    {
        this.status = status; this.user = user;
        setTitle("Daftar Barang " + status);
        setSize(900, 500);
        setLocationRelativeTo(parent);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Deskripsi", "Lokasi", "Tanggal"}, 0);
        table = new JTable(model);
        refreshData();

        // Inisialisasi Tombol
        JButton btnBack = new JButton("Kembali");
        JButton btnCreate = new JButton("Tambah Laporan Baru (+)");
        JButton btnUpdate = new JButton("Update Laporan Saya");

        // Menerapkan style biru ke setiap tombol
        styleButton(btnBack);
        styleButton(btnCreate);
        styleButton(btnUpdate);

        btnBack.addActionListener(e -> dispose());
        btnCreate.addActionListener(e -> new ReportItemMahasiswa(this, user, null).setVisible(true));
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0)
            {
                JOptionPane.showMessageDialog(this, "Pilih baris!");
                return;
            }
            String id = model.getValueAt(row, 0).toString();
            Item target = ItemRepository.getAllRaw().stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
            new ReportItemMahasiswa(this, user, target).setVisible(true);
        });

        // Panel tombol dengan sedikit padding
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE); // Background putih agar rapi
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.add(btnBack);
        p.add(btnCreate);
        p.add(btnUpdate);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
    }

    // Metode helper untuk desain tombol biru
    private void styleButton(JButton button) {
        button.setBackground(BLUE_THEME);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Menambahkan margin internal tombol
        button.setMargin(new Insets(5, 15, 5, 15));
    }

    public void refreshData()
    {
        model.setRowCount(0);
        List<Item> filtered = ItemRepository.getAllRaw().stream()
                .filter(i -> i.getStatus().equals(status))
                .collect(Collectors.toList());
        for (Item i : filtered)
            model.addRow(new Object[]{i.getId(), i.getNama(), i.getDeskripsi(), i.getLokasi(), i.getTanggal()});
    }
}