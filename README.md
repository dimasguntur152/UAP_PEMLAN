# 📑 Sistem Lost & Found Kampus - Java Swing Edition

Sistem **Lost & Found Kampus** adalah sistem yang dirancang untuk memfasilitasi mahasiswa dalam melaporkan barang hilang atau ditemukan di lingkungan kampus. Aplikasi ini memiliki dua level akses (Admin & Mahasiswa) dengan manajemen data menggunakan sistem penyimpanan file lokal (`.txt`).

---

## 🚀 Fitur Utama

### 👤 Akses Mahasiswa

* **Laporkan Barang**: Menginput laporan barang hilang atau ditemukan.
* **Input Validation**: Nama barang divalidasi secara otomatis (hanya boleh mengandung huruf dan spasi).
* **Update Laporan**: Mengubah informasi pada laporan yang sudah dibuat sebelumnya.
* **Riwayat Pribadi**: Melihat daftar barang yang pernah dilaporkan oleh akun tersebut.

### 🔑 Akses Admin

* **Manajemen Global**: Melihat seluruh daftar barang hilang dan ditemukan dari semua pengguna.
* **Penyelesaian Kasus**: Menghapus data barang yang sudah selesai diproses (diklaim/ditemukan) dan memindahkannya ke sistem riwayat permanen.
* **Validasi Seleksi**: Sistem pengaman yang memastikan admin harus memilih baris data sebelum melakukan penghapusan.

### 🛡️ Keamanan & Validasi

* **Sistem Login**: Autentikasi berbeda untuk Admin dan Mahasiswa.
* **Smart Feedback**: Pesan error yang detail jika login kosong atau kredensial salah.

---

## 🛠️ Persyaratan Sistem

Sebelum menjalankan aplikasi, pastikan perangkat Anda sudah terpasang:

1. **Java Development Kit (JDK)** versi 11 atau yang terbaru.
2. **IDE** (IntelliJ IDEA, Eclipse, atau NetBeans) — *Sangat direkomendasikan menggunakan IntelliJ IDEA*.

---

## 📂 Struktur Direktori

Pastikan folder `data` tersedia di root project agar program dapat menyimpan file:

```text
LostAndFound/
├── data/
│   ├── items.txt      # Menyimpan data barang aktif
│   └── history.txt    # Menyimpan data barang yang sudah selesai (Admin)
├── src/org/example/
│   ├── Main.java
│   ├── Login.java
│   ├── Dashboard.java
│   ├── Item.java
│   └── ... (file lainnya)
└── README.md

```

---

## 🏃 Cara Menjalankan Program

### Langkah 1: Persiapan File

Buka proyek di IDE pilihan Anda. Pastikan semua file berada di dalam package `org.example`.

### Langkah 2: Setup Database (Otomatis)

Program akan secara otomatis membuat folder `data` dan file `.txt` saat pertama kali dijalankan jika belum tersedia.

### Langkah 3: Menjalankan Aplikasi

Cari file `Main.java`, klik kanan, dan pilih **Run 'Main.main()'**.

### Langkah 4: Kredensial Login

Gunakan akun berikut untuk mencoba fitur:

| Role | Username | Password |
| --- | --- | --- |
| **Admin** | `Dimass` | `Admin152` |
| **Admin** | `Nasywah` | `Admin121` |
| **Mahasiswa** | `Dimas Guntur Prasetya Sumoro` | `2024152` |
| **Mahasiswa** | `Nasywah Dzufairah Azahra` | `2024121` |

## 📝 Panduan Penggunaan Fitur Laporan Barang

Bagian ini menjelaskan secara detail bagaimana pengguna (Mahasiswa dan Admin) menggunakan fitur **Create, Update, dan Delete** pada sistem **Lost & Found Kampus**.

---

## ➕ Create (Menambahkan Laporan Barang)

### 👤 Akses: Mahasiswa

Fitur ini digunakan oleh Mahasiswa untuk **melaporkan barang yang hilang atau ditemukan**.

#### 🔹 Langkah-langkah:

1. Login sebagai **Mahasiswa**
2. Masuk ke **Dashboard**
3. Klik menu **Lihat Daftar Barang**
4. Pilih kategori:

    * **Barang Hilang**
    * **Barang Ditemukan**
5. Klik tombol **Tambah Laporan Baru (+)**
6. Isi form laporan:

    * **Nama Barang**
      → Wajib diisi, hanya huruf dan spasi
    * **Deskripsi Barang**
      → Opsional (detail tambahan)
    * **Lokasi Terakhir / Ditemukan**
      → Wajib diisi
    * **Status Barang**
      → Hilang / Ditemukan
7. Klik tombol **Simpan**

#### ✅ Hasil:

* Data laporan akan tersimpan di file `items.txt`
* Barang langsung muncul di daftar sesuai kategorinya
* Nama pelapor otomatis terisi berdasarkan akun login

---

## ✏️ Update (Memperbarui Laporan Barang)

### 👤 Akses: Mahasiswa (hanya laporan milik sendiri)

Fitur ini digunakan untuk **memperbaiki atau memperbarui laporan yang sudah dibuat**, misalnya:

* Salah input nama
* Lokasi berubah
* Status barang berubah

#### 🔹 Langkah-langkah:

1. Login sebagai **Mahasiswa**
2. Masuk ke **Lihat Daftar Barang**
3. Pilih salah satu data barang di tabel
4. Klik tombol **Update Laporan Saya**
5. Form akan terbuka dengan data lama
6. Ubah data yang diperlukan
7. Klik **Simpan**

#### ⚠️ Validasi:

* Jika tidak memilih baris data → sistem menampilkan peringatan
* Nama barang tetap divalidasi (hanya huruf & spasi)

#### ✅ Hasil:

* Data lama akan **ditimpa (update)**, bukan membuat data baru
* ID barang tetap sama
* Perubahan langsung tersimpan ke file

---

## 🗑️ Delete / Selesaikan Laporan

### 👨‍💼 Akses: Admin

Fitur delete **tidak benar-benar menghapus data**, melainkan:

> **Memindahkan data ke Riwayat (history)**

Hal ini dilakukan agar data tetap terdokumentasi.

#### 🔹 Langkah-langkah:

1. Login sebagai **Admin**
2. Masuk ke **Lihat Daftar Barang**
3. Pilih kategori barang
4. Klik salah satu baris data pada tabel
5. Klik tombol **Selesai (Hapus)**
6. Konfirmasi penghapusan

#### ⚠️ Validasi:

* Jika tidak memilih baris → muncul pesan peringatan
* Konfirmasi wajib sebelum penghapusan

#### ✅ Hasil:

* Data dihapus dari `items.txt`
* Data dipindahkan ke `history.txt`
* Data dapat dilihat di menu **Riwayat**

---

## 🔄 Alur Singkat CRUD

1. **Create** → Mahasiswa menambahkan laporan
2. **Read** → Semua user melihat daftar sesuai role
3. **Update** → Mahasiswa memperbarui laporannya
4. **Delete (Logical)** → Admin memindahkan ke Riwayat

---

## 📖 Logika Bisnis & Teknis

* **Penyimpanan Data**: Menggunakan format CSV sederhana di dalam file teks dengan pemisah titik koma (`;`).
* **Validasi Regex**: Menggunakan `[a-zA-Z\\s]+` pada form laporan untuk memastikan integritas data nama barang.
* **GUI Layout**: Menggunakan kombinasi `GridBagLayout` untuk tampilan login yang responsif di tengah, dan `GridLayout` untuk tombol dashboard.

---

## 🎨 Tampilan UI

* **Warna Utama**: Putih Bersih & Abu-abu Modern (Login).
* **Komponen**: `JTable` untuk manajemen data, `JOptionPane` untuk interaksi user.

---

## 👥 Kontributor

* **Dimas Guntur Prasetya Sumoro** - Developer
* **Nasywah Dzufairah Azahra** - Developer

---

### 📝 Catatan Penting

> Jangan menghapus folder `data` secara manual saat program berjalan karena dapat menyebabkan `IOException`. Selalu gunakan tombol **Logout** untuk keluar aplikasi dengan aman.

---
