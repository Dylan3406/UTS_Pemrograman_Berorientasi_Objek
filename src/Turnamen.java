import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Turnamen.java
 * Kelas pengelola: menyimpan seluruh peserta dan riwayat pertandingan,
 * lalu menyediakan operasi pendaftaran, pencatatan hasil, dan klasemen.
 */
public class Turnamen {
    private String namaTurnamen;
    private List<Peserta> daftarPeserta;
    private List<Pertandingan> riwayatPertandingan;

    public Turnamen(String namaTurnamen) {
        this.namaTurnamen = namaTurnamen;
        this.daftarPeserta = new ArrayList<>();
        this.riwayatPertandingan = new ArrayList<>();
    }

    public String getNamaTurnamen() { return namaTurnamen; }
    public List<Peserta> getDaftarPeserta() { return daftarPeserta; }

    public void daftarkanPeserta(Peserta peserta) {
        daftarPeserta.add(peserta);
        System.out.println(">> Peserta \"" + peserta.getNama() + "\" berhasil didaftarkan.");
    }

    public void catatPertandingan(Pertandingan p) {
        riwayatPertandingan.add(p);
    }

    /** OVERLOADING #3: cari peserta berdasarkan ID (pencarian tunggal, exact match). */
    public Peserta cariPeserta(String id) {
        // LOOPING (for): menelusuri seluruh peserta
        for (Peserta p : daftarPeserta) {
            if (p.getIdPeserta().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    /** OVERLOADING #3: versi kedua, mencari berdasarkan potongan nama (bisa banyak hasil). */
    public List<Peserta> cariPeserta(String kataKunci, boolean berdasarkanNama) {
        List<Peserta> hasil = new ArrayList<>();
        // LOOPING (for) + CONDITION (if)
        for (Peserta p : daftarPeserta) {
            if (berdasarkanNama && p.getNama().toLowerCase().contains(kataKunci.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public void tampilkanSemuaPeserta() {
        if (daftarPeserta.isEmpty()) {
            System.out.println("Belum ada peserta terdaftar.");
            return;
        }
        System.out.println("\n===== DAFTAR PESERTA: " + namaTurnamen + " =====");
        for (Peserta p : daftarPeserta) {
            System.out.println("-----------------------------------");
            // p.tampilkanDetail() -> memanggil versi override sesuai jenis objek (POLYMORPHISM)
            System.out.println(p.tampilkanDetail());
        }
        System.out.println("-----------------------------------");
    }

    public void tampilkanRiwayatPertandingan() {
        if (riwayatPertandingan.isEmpty()) {
            System.out.println("Belum ada pertandingan tercatat.");
            return;
        }
        System.out.println("\n===== RIWAYAT PERTANDINGAN: " + namaTurnamen + " =====");
        int nomor = 1;
        for (Pertandingan p : riwayatPertandingan) {
            // p.tampilkanInfo() -> memanggil versi override sesuai jenis pertandingan (POLYMORPHISM)
            System.out.println(nomor + ". " + p.tampilkanInfo());
            nomor++;
        }
    }

    public void tampilkanKlasemen() {
        if (daftarPeserta.isEmpty()) {
            System.out.println("Belum ada peserta untuk ditampilkan di klasemen.");
            return;
        }
        List<Peserta> urut = new ArrayList<>(daftarPeserta);
        urut.sort(Comparator.comparingInt(Peserta::getPoin).reversed());

        System.out.println("\n===== KLASEMEN: " + namaTurnamen + " =====");
        int peringkat = 1;
        for (Peserta p : urut) {
            System.out.println(peringkat + ". " + p.infoRingkas());
            peringkat++;
        }
    }
}
