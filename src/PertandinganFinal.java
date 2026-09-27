/**
 * PertandinganFinal.java
 * Subclass dari Pertandingan (INHERITANCE hierarki #2, jalur 2).
 * Aturan: menang = 5 poin, tidak boleh seri, ada bonus jika menang sapu bersih.
 */
public class PertandinganFinal extends Pertandingan {
    private static final int POIN_MENANG = 5;
    private static final int BONUS_SAPU_BERSIH = 2;
    private boolean sapuBersih;

    public PertandinganFinal(String idPertandingan, Peserta pesertaA, Peserta pesertaB) {
        super(idPertandingan, pesertaA, pesertaB);
    }

    /**
     * OVERRIDING sekaligus bagian dari OVERLOADING: ini versi 1-parameter
     * yang wajib ada karena mengimplementasikan method abstrak dari superclass.
     * Method ini memanggil versi 2-parameter di bawah (delegasi).
     */
    @Override
    public void prosesHasil(Peserta pemenang) {
        prosesHasil(pemenang, false);
    }

    /**
     * OVERLOADING #2: method dengan nama sama "prosesHasil" tapi jumlah
     * parameter berbeda (menambahkan info sapu bersih). ContOh POLYMORPHISM
     * lewat method overloading di kelas yang sama.
     */
    public void prosesHasil(Peserta pemenang, boolean sapuBersih) {
        this.sapuBersih = sapuBersih;
        // CONDITION (if-else): final tidak boleh berakhir seri
        if (pemenang == null) {
            System.out.println("   -> Final tidak boleh berakhir seri! Hasil tidak diproses.");
            return;
        }
        this.pemenang = pemenang;
        int totalPoin = POIN_MENANG;
        if (sapuBersih) {
            totalPoin += BONUS_SAPU_BERSIH;
        }
        pemenang.tambahPoin(totalPoin, "Juara turnamen (Final)");
    }

    /** OVERRIDING: format tampilan khusus babak final. */
    @Override
    public String tampilkanInfo() {
        String hasil;
        if (pemenang == null) {
            hasil = "Belum ada hasil";
        } else if (sapuBersih) {
            hasil = "Menang Sapu Bersih: " + pemenang.getNama();
        } else {
            hasil = "Menang: " + pemenang.getNama();
        }
        return "[FINAL #" + idPertandingan + "] " + pesertaA.getNama()
                + " vs " + pesertaB.getNama() + " -> " + hasil;
    }
}
