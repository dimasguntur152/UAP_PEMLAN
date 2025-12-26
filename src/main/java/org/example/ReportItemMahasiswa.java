package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ReportItemMahasiswa extends JFrame
{
    // Warna biru tema yang konsisten
    private final Color BLUE_THEME = new Color(0, 102, 204);

    public ReportItemMahasiswa(JFrame parent, String user, Item itemToEdit)
    {
        setTitle(itemToEdit == null ? "Tambah Laporan" : "Update Laporan");
        setSize(500, 450);
        setLocationRelativeTo(parent);

        // Menggunakan panel utama dengan border agar ada jarak (padding)
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

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

        // Menerapkan style biru pada tombol
        styleButton(btnBatal);
        styleButton(btnSimpan);

        btnBatal.addActionListener(e -> dispose());
        btnSimpan.addActionListener(e -> {
            String nama = txtNama.getText().trim();
            if (nama.isEmpty() || txtLokasi.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Nama dan Lokasi wajib diisi!");
                return;
            }

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
            }
            catch (Exception ex) {}
        });

        mainPanel.add(new JLabel(" Nama Barang:")); mainPanel.add(txtNama);
        mainPanel.add(new JLabel(" Deskripsi:")); mainPanel.add(new JScrollPane(txtDeskripsi));
        mainPanel.add(new JLabel(" Lokasi:")); mainPanel.add(txtLokasi);
        mainPanel.add(new JLabel(" Status:")); mainPanel.add(cbStatus);
        mainPanel.add(btnBatal); mainPanel.add(btnSimpan);

        add(mainPanel);
    }

    // Metode helper untuk desain tombol biru
    private void styleButton(JButton button) {
        button.setBackground(BLUE_THEME);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}