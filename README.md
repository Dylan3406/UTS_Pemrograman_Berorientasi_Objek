# Aplikasi Manajemen Turnamen Esport (UTS PBO)

## 1. Deskripsi Proyek

Aplikasi ini adalah program **Command Line Interface (CLI)** berbasis Java yang
mensimulasikan sistem **Manajemen Turnamen Esport**. Program ini merupakan
pengembangan lanjutan dari tugas sebelumnya, dengan tambahan fitur pencatatan
pertandingan dan aturan poin yang lebih kompleks.

**Fungsi utama program:**
- Mendaftarkan peserta turnamen, baik yang bertanding **perorangan (solo)**
  maupun **beregu (tim)**.
- Mencatat hasil pertandingan pada **babak penyisihan** (poin menang 3, seri 1)
  maupun **babak final** (poin menang 5, tidak boleh seri, ada bonus jika menang
  sapu bersih).
- Menampilkan daftar peserta, riwayat seluruh pertandingan, dan klasemen akhir
  yang otomatis terurut berdasarkan poin tertinggi.
- Mencari peserta berdasarkan potongan nama.

**Konsep OOP yang diterapkan:**

| Konsep | Implementasi |
|---|---|
| **Inheritance (2 hierarki)** | `PesertaSolo` & `PesertaTim` extends `Peserta`; `PertandinganPenyisihan` & `PertandinganFinal` extends `Pertandingan` |
| **Polymorphism - Overriding** | `tampilkanDetail()` pada `Peserta`, `prosesHasil()` & `tampilkanInfo()` pada `Pertandingan` diimplementasikan berbeda di tiap subclass |
| **Polymorphism - Overloading** | `tambahPoin(int)` vs `tambahPoin(int, String)` di kelas `Peserta`; `prosesHasil(Peserta)` vs `prosesHasil(Peserta, boolean)` di `PertandinganFinal`; `cariPeserta(String)` vs `cariPeserta(String, boolean)` di `Turnamen` |
| **Condition (if-else)** | Validasi ID peserta, penentuan pemenang/seri, aturan final tidak boleh seri, dsb. |
| **Looping** | `do-while` untuk menu utama, `for` untuk menelusuri daftar peserta/pertandingan/klasemen, dan input anggota tim |

## 2. Alur Program

### Struktur Kelas Singkat
```
Peserta (abstract)                 Pertandingan (abstract)
  ├── PesertaSolo                    ├── PertandinganPenyisihan
  └── PesertaTim                     └── PertandinganFinal

Turnamen  -> mengelola List<Peserta> dan List<Pertandingan>
Main      -> menu CLI yang menghubungkan semua kelas di atas
```

### Cara Menjalankan
```bash
cd src
javac *.java
java Main
```

### Cara Kerja Sistem
1. Saat dijalankan, program menampilkan **menu utama** dalam sebuah loop
   (`do-while`) yang terus muncul sampai pengguna memilih `0` untuk keluar.
2. **Menu 1 & 2** mendaftarkan peserta baru (solo atau tim). Untuk tim, program
   akan meminta input nama anggota sebanyak jumlah yang dimasukkan (looping `for`).
3. **Menu 4** mencatat hasil pertandingan babak penyisihan: pengguna memilih dua
   peserta yang bertanding, lalu menentukan pemenang atau hasil seri. Poin akan
   otomatis ditambahkan sesuai aturan penyisihan (menang = 3, seri = 1 untuk
   masing-masing).
4. **Menu 5** mencatat hasil pertandingan final: aturan lebih ketat (tidak boleh
   seri) dan poin menang = 5, ditambah bonus 2 poin jika menang secara sapu bersih.
5. **Menu 6 & 7** menampilkan riwayat seluruh pertandingan dan klasemen akhir
   (diurutkan otomatis dari poin tertinggi).
6. **Menu 8** mencari peserta berdasarkan potongan nama yang dimasukkan.
7. Seluruh validasi input (ID tidak ditemukan, peserta A = peserta B, final tidak
   boleh seri, dsb.) ditangani menggunakan struktur **if-else**.

## 3. Penjelasan Gambar (Screenshot Output)


Gambar di atas menunjukkan potongan hasil menjalankan program setelah beberapa
pertandingan dicatat:
- **Riwayat Pertandingan (menu 6)** menampilkan tiga pertandingan yang sudah
  tercatat: dua pertandingan penyisihan (satu dengan pemenang, satu berakhir
  seri) dan satu pertandingan final yang dimenangkan secara sapu bersih oleh
  tim RRQ Sakti. Baris ini membuktikan method `tampilkanInfo()` bekerja berbeda
  untuk `PertandinganPenyisihan` dan `PertandinganFinal` (overriding).
- **Klasemen (menu 7)** menampilkan daftar peserta yang sudah terurut otomatis
  dari poin tertinggi ke terendah, membuktikan bahwa poin dari pertandingan
  penyisihan maupun final terakumulasi dengan benar ke masing-masing peserta.
