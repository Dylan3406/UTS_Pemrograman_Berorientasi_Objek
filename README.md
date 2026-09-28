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
<img width="511" height="182" alt="image" src="https://github.com/user-attachments/assets/426bcf3a-35eb-4d5a-b9aa-700316a187fe" /> <br>
Program menampilkan menu utama dari Aplikasi Manajemen Turnamen Esport untuk ajang Kaltim Esport Championship 2026. Antarmuka baris perintah (CLI) ini menyediakan beberapa opsi navigasi bagi pengguna, yang dimulai dari fitur pendaftaran peserta baik secara perorangan (solo) melalui menu pertama maupun secara beregu melalui menu kedua. Selain itu, terdapat fitur untuk melihat daftar seluruh peserta yang sudah terdaftar serta memperbarui skor atau hasil pertandingan peserta terkait. Program ini juga dilengkapi dengan menu untuk menampilkan klasemen sementara turnamen serta opsi untuk keluar dari aplikasi. <br>

<img width="308" height="114" alt="image" src="https://github.com/user-attachments/assets/5b6346e3-cab8-4924-bb27-a934b5cbf4e7" /> <br>
Gambar menampilkan antarmuka program pada submenu Pendaftaran Peserta Solo. Pada bagian ini, sistem meminta dan merekam data identitas pemain perorangan yang meliputi ID Peserta bernomor S001, nama pemain yaitu Arzad, gamer tag gyegy, serta game yang diikuti yaitu efootball. Setelah seluruh data tersebut diisi, program memberikan konfirmasi berupa teks pemberitahuan bahwa peserta atas nama Arzad berhasil didaftarkan.<br>

<img width="328" height="180" alt="image" src="https://github.com/user-attachments/assets/f49dee71-6501-4fd6-805f-c0a46364f946" /> <br>
Gambar menampilkan antarmuka program pada submenu Pendaftaran Peserta Tim. Pada bagian ini, sistem meminta dan merekam informasi detail mengenai regu atau tim yang mendaftar, yang mencakup ID Tim T001, nama tim Nyawit, nama kapten Alip, serta game yang diikuti yaitu Free Fire. Selain itu, program juga mencatat jumlah anggota tambahan sebanyak tiga orang beserta nama-nama anggotanya yang meliputi hakim, jeje, dan rama. Setelah seluruh data tim tersebut terisi dengan lengkap, program memberikan konfirmasi berupa teks pemberitahuan bahwa peserta tim dengan nama Nyawit berhasil didaftarkan. <br>

<img width="450" height="310" alt="image" src="https://github.com/user-attachments/assets/c8f9349a-ec56-4a24-b40a-77b5cce74749" /> <br>
Gambar menampilkan antarmuka program pada menu Tampilkan Semua Peserta untuk ajang Kaltim Esport Championship 2026. Pada bagian ini, program menyajikan daftar lengkap peserta yang terbagi menjadi dua kategori, yaitu peserta solo dan peserta tim. Untuk kategori peserta solo, ditampilkan rincian ID S001, nama Arzad, gamer tag gyegy, game yang diikuti efootball, serta perolehan poin awal yaitu 0. Sementara itu, pada kategori peserta tim, ditampilkan informasi ID T001, nama tim Nyawit, kapten Alip, jumlah anggota sebanyak 4 orang yang terdiri dari Alip, hakim, jeje, dan rama, game yang diikuti Free Fire, serta perolehan poin awal sebesar 0.


- **Riwayat Pertandingan (menu 6)** menampilkan tiga pertandingan yang sudah
  tercatat: dua pertandingan penyisihan (satu dengan pemenang, satu berakhir
  seri) dan satu pertandingan final yang dimenangkan secara sapu bersih oleh
  tim RRQ Sakti. Baris ini membuktikan method `tampilkanInfo()` bekerja berbeda
  untuk `PertandinganPenyisihan` dan `PertandinganFinal` (overriding).
- **Klasemen (menu 7)** menampilkan daftar peserta yang sudah terurut otomatis
  dari poin tertinggi ke terendah, membuktikan bahwa poin dari pertandingan
  penyisihan maupun final terakumulasi dengan benar ke masing-masing peserta.
