package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListAdmin extends JFrame
{
    private DefaultTableModel model;
    private JTable table;
    private String status;

    private final Color BLUE_THEME = new Color(0, 102, 204);

    public ItemListAdmin(JFrame parent, String status)
    {
        this.status = status;
        setTitle("Admin - Daftar Barang " + status);
        setSize(900, 500);
        setLocationRelativeTo(parent);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Lokasi", "Pelapor"}, 0);
        table = new JTable(model);
        refreshData();

        JButton btnBack = new JButton("Kembali");
        JButton btnDelete = new JButton("Selesai (Hapus)");

        styleButton(btnBack);
        styleButton(btnDelete);

        btnBack.addActionListener(e -> dispose());

        // Logika Hapus dengan Validasi Seleksi Baris
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Silakan pilih baris/data yang ingin dihapus terlebih dahulu!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin barang ini sudah selesai diproses?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION)
            {
                try
                {
                    String id = model.getValueAt(selectedRow, 0).toString();

                    Item target = ItemRepository.getAllRaw().stream()
                            .filter(i -> i.getId().equals(id))
                            .findFirst()
                            .orElse(null);

                    if (target != null)
                    {
                        ItemRepository.deleteToHistory(target);
                        JOptionPane.showMessageDialog(this, "Data berhasil dipindahkan ke Riwayat!");
                        refreshData();
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data.");
                    ex.printStackTrace();
                }
            }
        });

        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(Color.WHITE);
        panelBawah.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBawah.add(btnBack);
        panelBawah.add(btnDelete);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setBackground(BLUE_THEME);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 35)); // Ukuran tombol yang seragam
    }

    public void refreshData()
    {
        model.setRowCount(0);
        List<Item> items = ItemRepository.getAllRaw().stream()
                .filter(i -> i.getStatus().equals(status))
                .collect(Collectors.toList());

        for (Item i : items)
        {
            model.addRow(new Object[]{i.getId(), i.getNama(), i.getLokasi(), i.getReporter()});
        }
    }
}