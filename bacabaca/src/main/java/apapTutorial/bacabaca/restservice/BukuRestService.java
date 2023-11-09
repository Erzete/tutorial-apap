package apapTutorial.bacabaca.restservice;

import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.rest.BukuDetail;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface BukuRestService {
    void createRestBuku(Buku buku);
    void updateRestBuku(Buku buku);
    List<Buku> retrieveBukuByJudul(String judul);
    List<Buku> retrieveRestAllBuku();
    Buku getRestBukuById(UUID id);
    Mono<String> getStatus();
    Mono<BukuDetail> postStatus();
}
