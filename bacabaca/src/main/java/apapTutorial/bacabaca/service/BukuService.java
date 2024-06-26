package apapTutorial.bacabaca.service;
import apapTutorial.bacabaca.model.Buku;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BukuService {
    void saveBuku(Buku buku); // Tambah Buku
    List<Buku> getAllBuku(); // Get list buku
    Buku getBukuById(UUID id); // Get buku based on ID
    boolean isJudulExist(String judul);
    boolean isJudulExist(String judul, UUID id);
    Buku updateBuku(Buku buku);
    void deleteBuku(Buku buku);
    List<Buku> findBukuByJudul(String judul);
    Map<String, Float> getMonthPopularBook();
}
// public interface BukuService {
//     void createBuku(Buku buku);

//     List<Buku> getAllBuku();

//     Buku getBukuById(UUID id);

//     void deleteBuku(Buku buku);

//     boolean adaBuku(String judul);

//     boolean adaBukuUpdate(String judul, UUID id);
// }
