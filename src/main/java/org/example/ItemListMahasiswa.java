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

    public ItemListMahasiswa(JFrame parent, String status, String user)
    {
        this.status = status; this.user = user;
        setTitle("Daftar Barang " + status);
        setSize(900, 500);
        setLocationRelativeTo(parent);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Deskripsi", "Lokasi", "Tanggal"}, 0);
        table = new JTable(model);
        refreshData();

        JButton btnBack = new JButton("Kembali");
        JButton btnCreate = new JButton("Tambah Laporan Baru (+)");
        JButton btnUpdate = new JButton("Update Laporan Saya");

        btnBack.addActionListener(e -> dispose());
        btnCreate.addActionListener(e -> new ReportItemMahasiswa(this, user, null).setVisible(true));
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih baris!"); return; }
            String id = model.getValueAt(row, 0).toString();
            Item target = ItemRepository.getAllRaw().stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
            new ReportItemMahasiswa(this, user, target).setVisible(true);
        });

        JPanel p = new JPanel();
        p.add(btnBack); p.add(btnCreate); p.add(btnUpdate);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
    }

    public void refreshData()
    {
        model.setRowCount(0);
        List<Item> filtered = ItemRepository.getAllRaw().stream().filter(i -> i.getStatus().equals(status)).collect(Collectors.toList());
        for (Item i : filtered) model.addRow(new Object[]{i.getId(), i.getNama(), i.getDeskripsi(), i.getLokasi(), i.getTanggal()});
    }
}