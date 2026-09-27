/**
 * Pertandingan.java
 * SUPERCLASS ABSTRAK #2 (hierarki inheritance kedua, terpisah dari Peserta).
 * Merepresentasikan satu pertandingan antara dua peserta.
 */
public abstract class Pertandingan {
    protected String idPertandingan;
    protected Peserta pesertaA;
    protected Peserta pesertaB;
    protected Peserta pemenang; // null jika seri / belum ada hasil

    public Pertandingan(String idPertandingan, Peserta pesertaA, Peserta pesertaB) {
        this.idPertandingan = idPertandingan;
        this.pesertaA = pesertaA;
        this.pesertaB = pesertaB;
        this.pemenang = null;
    }

    public String getIdPertandingan() { return idPertandingan; }
    public Peserta getPemenang() { return pemenang; }

    /** Diimplementasikan berbeda oleh setiap subclass (aturan poin berbeda). */
    public abstract void prosesHasil(Peserta pemenang);

    /** Diimplementasikan berbeda oleh setiap subclass (format info berbeda). */
    public abstract String tampilkanInfo();
}
