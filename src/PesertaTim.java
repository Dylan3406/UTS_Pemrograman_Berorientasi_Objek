import java.util.ArrayList;
import java.util.List;

/**
 * PesertaTim.java
 * Subclass dari Peserta (INHERITANCE hierarki #1, jalur 2).
 */
public class PesertaTim extends Peserta {
    private String kapten;
    private List<String> anggotaTim;

    public PesertaTim(String idPeserta, String namaTim, String gameYangDiikuti, String kapten) {
        super(idPeserta, namaTim, gameYangDiikuti);
        this.kapten = kapten;
        this.anggotaTim = new ArrayList<>();
        this.anggotaTim.add(kapten);
    }

    public String getKapten() { return kapten; }
    public List<String> getAnggotaTim() { return anggotaTim; }

    public void tambahAnggota(String namaPemain) {
        anggotaTim.add(namaPemain);
    }

    /** OVERRIDING: implementasi berbeda dari superclass Peserta. */
    @Override
    public String tampilkanDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Peserta Tim ===\n");
        sb.append("ID          : ").append(getIdPeserta()).append("\n");
        sb.append("Nama Tim    : ").append(getNama()).append("\n");
        sb.append("Kapten      : ").append(kapten).append("\n");
        sb.append("Jml Anggota : ").append(anggotaTim.size()).append("\n");
        sb.append("Anggota     : ").append(String.join(", ", anggotaTim)).append("\n");
        sb.append("Game        : ").append(getGameYangDiikuti()).append("\n");
        sb.append("Poin        : ").append(getPoin());
        return sb.toString();
    }
}
