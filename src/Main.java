import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 * Entry point aplikasi CLI Manajemen Turnamen Esport (UTS PBO).
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Turnamen turnamen = new Turnamen("Kaltim Esport Championship 2026");

    public static void main(String[] args) {
        int pilihan;
        // LOOPING (do-while): menu utama akan terus tampil sampai user memilih 0
        do {
            tampilkanMenu();
            pilihan = bacaAngka();

            // CONDITION (if-else berlapis / switch)
            switch (pilihan) {
                case 1: tambahPesertaSolo(); break;
                case 2: tambahPesertaTim(); break;
                case 3: turnamen.tampilkanSemuaPeserta(); break;
                case 4: catatHasilPenyisihan(); break;
                case 5: catatHasilFinal(); break;
                case 6: turnamen.tampilkanRiwayatPertandingan(); break;
                case 7: turnamen.tampilkanKlasemen(); break;
                case 8: cariPesertaMenu(); break;
                case 0: System.out.println("Terima kasih, sampai jumpa!"); break;
                default: System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=====================================================");
        System.out.println(" APLIKASI MANAJEMEN TURNAMEN ESPORT - " + turnamen.getNamaTurnamen());
        System.out.println("=====================================================");
        System.out.println("1. Daftarkan Peserta Solo");
        System.out.println("2. Daftarkan Peserta Tim");
        System.out.println("3. Tampilkan Semua Peserta");
        System.out.println("4. Catat Hasil Pertandingan Penyisihan");
        System.out.println("5. Catat Hasil Pertandingan Final");
        System.out.println("6. Tampilkan Riwayat Pertandingan");
        System.out.println("7. Tampilkan Klasemen");
        System.out.println("8. Cari Peserta Berdasarkan Nama");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static int bacaAngka() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void tambahPesertaSolo() {
        System.out.println("\n-- Pendaftaran Peserta Solo --");
        System.out.print("ID Peserta       : ");
        String id = scanner.nextLine().trim();
        System.out.print("Nama Pemain      : ");
        String nama = scanner.nextLine().trim();
        System.out.print("Gamer Tag        : ");
        String gamerTag = scanner.nextLine().trim();
        System.out.print("Game yang Diikuti: ");
        String game = scanner.nextLine().trim();

        turnamen.daftarkanPeserta(new PesertaSolo(id, nama, game, gamerTag));
    }

    private static void tambahPesertaTim() {
        System.out.println("\n-- Pendaftaran Peserta Tim --");
        System.out.print("ID Tim           : ");
        String id = scanner.nextLine().trim();
        System.out.print("Nama Tim         : ");
        String nama = scanner.nextLine().trim();
        System.out.print("Nama Kapten      : ");
        String kapten = scanner.nextLine().trim();
        System.out.print("Game yang Diikuti: ");
        String game = scanner.nextLine().trim();

        PesertaTim tim = new PesertaTim(id, nama, game, kapten);

        System.out.print("Jumlah anggota tambahan (selain kapten): ");
        int jumlah = bacaAngka();
        // LOOPING (for): input anggota tim sebanyak "jumlah"
        for (int i = 1; i <= jumlah && jumlah > 0; i++) {
            System.out.print("  Nama anggota ke-" + i + ": ");
            tim.tambahAnggota(scanner.nextLine().trim());
        }

        turnamen.daftarkanPeserta(tim);
    }

    /** Mengambil 2 peserta yang akan bertanding, dengan validasi if-else. */
    private static Peserta[] pilihDuaPeserta() {
        turnamen.tampilkanSemuaPeserta();
        System.out.print("Masukkan ID Peserta A: ");
        String idA = scanner.nextLine().trim();
        System.out.print("Masukkan ID Peserta B: ");
        String idB = scanner.nextLine().trim();

        Peserta a = turnamen.cariPeserta(idA);
        Peserta b = turnamen.cariPeserta(idB);

        // CONDITION (if-else): validasi input
        if (a == null || b == null) {
            System.out.println(">> ID peserta tidak ditemukan.");
            return null;
        } else if (idA.equalsIgnoreCase(idB)) {
            System.out.println(">> Peserta A dan B tidak boleh sama.");
            return null;
        }
        return new Peserta[]{a, b};
    }

    private static void catatHasilPenyisihan() {
        System.out.println("\n-- Catat Hasil Pertandingan Penyisihan --");
        Peserta[] dua = pilihDuaPeserta();
        if (dua == null) return;

        System.out.print("ID Pertandingan  : ");
        String idPtd = scanner.nextLine().trim();
        System.out.println("Siapa yang menang? (1=" + dua[0].getNama() + ", 2=" + dua[1].getNama() + ", 3=Seri)");
        int pilih = bacaAngka();

        PertandinganPenyisihan pertandingan = new PertandinganPenyisihan(idPtd, dua[0], dua[1]);
        // CONDITION (if-else)
        if (pilih == 1) {
            pertandingan.prosesHasil(dua[0]);
        } else if (pilih == 2) {
            pertandingan.prosesHasil(dua[1]);
        } else {
            pertandingan.prosesHasil(null); // seri
        }
        turnamen.catatPertandingan(pertandingan);
        System.out.println(">> Hasil pertandingan tercatat: " + pertandingan.tampilkanInfo());
    }

    private static void catatHasilFinal() {
        System.out.println("\n-- Catat Hasil Pertandingan Final --");
        Peserta[] dua = pilihDuaPeserta();
        if (dua == null) return;

        System.out.print("ID Pertandingan  : ");
        String idPtd = scanner.nextLine().trim();
        System.out.println("Siapa yang menang? (1=" + dua[0].getNama() + ", 2=" + dua[1].getNama() + ")");
        int pilih = bacaAngka();
        System.out.print("Apakah menang sapu bersih (tanpa kalah satu game pun)? (y/n): ");
        boolean sapuBersih = scanner.nextLine().trim().equalsIgnoreCase("y");

        PertandinganFinal pertandingan = new PertandinganFinal(idPtd, dua[0], dua[1]);
        // CONDITION (if-else) + memanggil OVERLOADED method prosesHasil(pemenang, sapuBersih)
        if (pilih == 1) {
            pertandingan.prosesHasil(dua[0], sapuBersih);
        } else if (pilih == 2) {
            pertandingan.prosesHasil(dua[1], sapuBersih);
        } else {
            System.out.println(">> Pilihan tidak valid, final tidak boleh seri.");
            return;
        }
        turnamen.catatPertandingan(pertandingan);
        System.out.println(">> Hasil final tercatat: " + pertandingan.tampilkanInfo());
    }

    private static void cariPesertaMenu() {
        System.out.print("\nMasukkan kata kunci nama: ");
        String kataKunci = scanner.nextLine().trim();
        List<Peserta> hasil = turnamen.cariPeserta(kataKunci, true);

        // CONDITION (if-else)
        if (hasil.isEmpty()) {
            System.out.println(">> Tidak ada peserta dengan nama mengandung \"" + kataKunci + "\".");
        } else {
            System.out.println(">> Ditemukan " + hasil.size() + " peserta:");
            for (Peserta p : hasil) {
                System.out.println("- " + p.infoRingkas());
            }
        }
    }
}
