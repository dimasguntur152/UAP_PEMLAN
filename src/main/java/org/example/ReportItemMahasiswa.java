package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ReportItemMahasiswa extends JFrame
{
    public ReportItemMahasiswa(JFrame parent, String user, Item itemToEdit)
    {
        setTitle(itemToEdit == null ? "Tambah Laporan" : "Update Laporan");
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtNama = new JTextField();
        JTextArea txtDeskripsi = new JTextArea();
        JTextField txtLokasi = new JTextField();
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"HILANG", "DITEMUKAN"});

        if (itemToEdit != null)
        {
            txtNama.setText(itemToEdit.getNama());
            txtDeskripsi.setText(itemToEdit.getDeskripsi());
            txtLokasi.setText(itemToEdit.getLokasi());
            cbStatus.setSelectedItem(itemToEdit.getStatus());
        }

        JButton btnBatal = new JButton("Batal");
        JButton btnSimpan = new JButton("Simpan");

        btnBatal.addActionListener(e -> dispose());
        btnSimpan.addActionListener(e -> {
            String nama = txtNama.getText().trim();
            if (nama.isEmpty() || txtLokasi.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Nama dan Lokasi wajib diisi!");
                return;
            }

            // VALIDASI: Hanya Huruf dan Spasi
            if (!nama.matches("[a-zA-Z\\s]+"))
            {
                JOptionPane.showMessageDialog(this, "Nama Barang hanya boleh berisi huruf!");
                return;
            }

            try
            {
                String id = (itemToEdit == null) ? UUID.randomUUID().toString().substring(0, 5) : itemToEdit.getId();
                Item newItem = new Item(id, nama, txtDeskripsi.getText(), txtLokasi.getText(), cbStatus.getSelectedItem().toString(), user);
                ItemRepository.saveOrUpdate(newItem);
                JOptionPane.showMessageDialog(this, "Berhasil!");
                if (parent instanceof ItemListMahasiswa) ((ItemListMahasiswa) parent).refreshData();
                dispose();
            } catch (Exception ex) {}
        });

        add(new JLabel(" Nama Barang:")); add(txtNama);
        add(new JLabel(" Deskripsi:")); add(new JScrollPane(txtDeskripsi));
        add(new JLabel(" Lokasi:")); add(txtLokasi);
        add(new JLabel(" Status:")); add(cbStatus);
        add(btnBatal); add(btnSimpan);
    }
}