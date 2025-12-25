package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListAdmin extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private String status;

    public ItemListAdmin(JFrame parent, String status) {
        this.status = status;
        setTitle("Admin - Daftar Barang " + status);
        setSize(900, 500);
        setLocationRelativeTo(parent);

        // Inisialisasi Tabel
        model = new DefaultTableModel(new String[]{"ID", "Nama", "Lokasi", "Pelapor"}, 0);
        table = new JTable(model);
        refreshData();

        // Tombol-tombol
        JButton btnBack = new JButton("Kembali");
        JButton btnDelete = new JButton("Selesai (Hapus)");

        // Logika Kembali
        btnBack.addActionListener(e -> dispose());

        // Logika Hapus dengan Validasi Seleksi Baris
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            // 1. VALIDASI: Cek apakah ada baris yang dipilih
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Silakan pilih baris/data yang ingin dihapus terlebih dahulu!");
                return; // Berhenti jika tidak ada baris yang dipilih
            }

            // 2. Konfirmasi Hapus
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin barang ini sudah selesai diproses?",
                    "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String id = model.getValueAt(selectedRow, 0).toString();

                    // Ambil data item dari repository berdasarkan ID
                    Item target = ItemRepository.getAllRaw().stream()
                            .filter(i -> i.getId().equals(id))
                            .findFirst()
                            .orElse(null);

                    if (target != null) {
                        // Pindahkan ke riwayat dan hapus dari file utama
                        ItemRepository.deleteToHistory(target);
                        JOptionPane.showMessageDialog(this, "Data berhasil dipindahkan ke Riwayat!");
                        refreshData(); // Segarkan tabel
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data.");
                    ex.printStackTrace();
                }
            }
        });

        // Panel Bawah
        JPanel panelBawah = new JPanel();
        panelBawah.add(btnBack);
        panelBawah.add(btnDelete);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);
    }

    public void refreshData() {
        model.setRowCount(0);
        List<Item> items = ItemRepository.getAllRaw().stream()
                .filter(i -> i.getStatus().equals(status))
                .collect(Collectors.toList());

        for (Item i : items) {
            model.addRow(new Object[]{i.getId(), i.getNama(), i.getLokasi(), i.getReporter()});
        }
    }
}