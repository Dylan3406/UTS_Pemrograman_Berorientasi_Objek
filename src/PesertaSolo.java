/**
 * PesertaSolo.java
 * Subclass dari Peserta (INHERITANCE hierarki #1, jalur 1).
 */
public class PesertaSolo extends Peserta {
    private String gamerTag;

    public PesertaSolo(String idPeserta, String nama, String gameYangDiikuti, String gamerTag) {
        super(idPeserta, nama, gameYangDiikuti);
        this.gamerTag = gamerTag;
    }

    public String getGamerTag() { return gamerTag; }

    /** OVERRIDING: implementasi berbeda dari superclass Peserta. */
    @Override
    public String tampilkanDetail() {
        return "=== Peserta Solo ===\n"
                + "ID          : " + getIdPeserta() + "\n"
                + "Nama        : " + getNama() + "\n"
                + "Gamer Tag   : " + gamerTag + "\n"
                + "Game        : " + getGameYangDiikuti() + "\n"
                + "Poin        : " + getPoin();
    }
}
