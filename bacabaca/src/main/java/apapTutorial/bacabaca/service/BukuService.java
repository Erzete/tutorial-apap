package apapTutorial.bacabaca.service;
import apapTutorial.bacabaca.model.Buku;
import java.util.List;
import java.util.UUID;

public interface BukuService {
    void createBuku(Buku buku);

    List<Buku> getAllBuku();

    Buku getBukuById(UUID id);

    void deleteBuku(Buku buku);

    boolean adaBuku(String judul);

    boolean adaBukuUpdate(String judul, UUID id);
}
