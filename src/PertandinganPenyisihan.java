/**
 * PertandinganPenyisihan.java
 * Subclass dari Pertandingan (INHERITANCE hierarki #2, jalur 1).
 * Aturan: menang = 3 poin, seri = 1 poin untuk masing-masing peserta.
 */
public class PertandinganPenyisihan extends Pertandingan {
    private static final int POIN_MENANG = 3;
    private static final int POIN_SERI = 1;

    public PertandinganPenyisihan(String idPertandingan, Peserta pesertaA, Peserta pesertaB) {
        super(idPertandingan, pesertaA, pesertaB);
    }

    /** OVERRIDING: aturan poin khusus babak penyisihan (mengizinkan seri). */
    @Override
    public void prosesHasil(Peserta pemenang) {
        this.pemenang = pemenang;
        // CONDITION (if-else): menentukan pembagian poin
        if (pemenang == null) {
            pesertaA.tambahPoin(POIN_SERI, "Hasil seri di babak penyisihan");
            pesertaB.tambahPoin(POIN_SERI, "Hasil seri di babak penyisihan");
        } else if (pemenang == pesertaA) {
            pesertaA.tambahPoin(POIN_MENANG, "Menang babak penyisihan");
        } else if (pemenang == pesertaB) {
            pesertaB.tambahPoin(POIN_MENANG, "Menang babak penyisihan");
        }
    }

    /** OVERRIDING: format tampilan khusus babak penyisihan. */
    @Override
    public String tampilkanInfo() {
        String hasil = (pemenang == null) ? "Seri" : "Menang: " + pemenang.getNama();
        return "[PENYISIHAN #" + idPertandingan + "] " + pesertaA.getNama()
                + " vs " + pesertaB.getNama() + " -> " + hasil;
    }
}
