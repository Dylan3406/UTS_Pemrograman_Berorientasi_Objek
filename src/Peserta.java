/**
 * Peserta.java
 * SUPERCLASS ABSTRAK #1 (hierarki inheritance pertama).
 * Merepresentasikan peserta turnamen esport secara umum.
 */
public abstract class Peserta {
    private String idPeserta;
    private String nama;
    private String gameYangDiikuti;
    private int poin;

    public Peserta(String idPeserta, String nama, String gameYangDiikuti) {
        this.idPeserta = idPeserta;
        this.nama = nama;
        this.gameYangDiikuti = gameYangDiikuti;
        this.poin = 0;
    }

    public String getIdPeserta() { return idPeserta; }
    public String getNama() { return nama; }
    public String getGameYangDiikuti() { return gameYangDiikuti; }
    public int getPoin() { return poin; }

    /**
     * OVERLOADING #1: dua method dengan nama sama "tambahPoin" tapi
     * parameter berbeda. Ini contoh POLYMORPHISM lewat method overloading,
     * dan diwariskan otomatis ke semua subclass (PesertaSolo & PesertaTim).
     */
    public void tambahPoin(int nilai) {
        if (nilai > 0) {
            this.poin += nilai;
        }
    }

    public void tambahPoin(int nilai, String keterangan) {
        tambahPoin(nilai); // memanggil versi 1 parameter di atas
        System.out.println("   -> Keterangan poin " + idPeserta + ": " + keterangan);
    }

    /** Method abstrak: WAJIB di-override berbeda oleh setiap subclass (OVERRIDING). */
    public abstract String tampilkanDetail();

    public String infoRingkas() {
        return String.format("[%s] %s | Game: %s | Poin: %d",
                idPeserta, nama, gameYangDiikuti, poin);
    }
}
