package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class HistoryList extends JFrame
{
    public HistoryList(JFrame parent, String role, String user)
    {
        setTitle("Riwayat");
        setSize(900, 500);
        setLocationRelativeTo(parent);
        DefaultTableModel model = new DefaultTableModel(new String[]{"Nama", "Status", "Pelapor", "Tanggal"}, 0);
        String file = role.equals("Admin") ? "data/history.txt" : "data/items.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                Item i = Item.fromFile(line);
                if (role.equals("Mahasiswa") && !i.getReporter().equals(user)) continue;
                model.addRow(new Object[]{i.getNama(), i.getStatus(), i.getReporter(), i.getTanggal()});
            }
        }
        catch (Exception e) {}
        add(new JScrollPane(new JTable(model)));
        JButton back = new JButton("Tutup");
        back.addActionListener(e -> dispose());
        add(back, BorderLayout.SOUTH);
    }
}