package org.example;
import java.time.LocalDate;

public class Item
{
    private String id;
    private String nama;
    private String deskripsi;
    private String lokasi;
    private String status;
    private String reporter;
    private LocalDate tanggal;

    public Item(String id, String nama, String deskripsi, String lokasi, String status, String reporter)
    {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.status = status;
        this.reporter = reporter;
        this.tanggal = LocalDate.now();
    }

    public Item(String id, String nama, String deskripsi, String lokasi, String status, String reporter, String tanggal)
    {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.status = status;
        this.reporter = reporter;
        this.tanggal = LocalDate.parse(tanggal);
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public String getLokasi() {
        return lokasi;
    }
    public String getStatus() {
        return status;
    }
    public String getReporter() {
        return reporter;
    }
    public LocalDate getTanggal() {
        return tanggal;
    }

    public String toFileString()
    {
        return id + ";" + nama + ";" + deskripsi + ";" + lokasi + ";" + status + ";" + reporter + ";" + tanggal;
    }

    public static Item fromFile(String line)
    {
        String[] d = line.split(";");
        return new Item(d[0], d[1], d[2], d[3], d[4], d[5], d[6]);
    }
}