package apapTutorial.bacabaca.restservice;

import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.repository.BukuDb;
import apapTutorial.bacabaca.rest.BukuDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BukuRestServiceImpl implements BukuRestService {
    @Autowired
    private BukuDb bukuDb;

    @Override
    public void createRestBuku(Buku buku) {
        bukuDb.save(buku);
    }

    @Override
    public void updateRestBuku(Buku buku) {
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> retrieveBukuByJudul(String judul) {
        return bukuDb.findByJudulContainingIgnoreCaseOrderByJudul(judul);
    }
    
    @Override
    public List<Buku> retrieveRestAllBuku() {
        return bukuDb.findAll();
    }

    @Override
    public Buku getRestBukuById(UUID id) {
        for (Buku buku: retrieveRestAllBuku()) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    }

    private final String mockUrl = "https://53cc02f9-5533-4bf2-8222-38522bd65663.mock.pstmn.io";

    private final WebClient webClient;

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(mockUrl).build(); // mock server
    }

    @Override
    public Mono<String> getStatus() {
        return this.webClient.get().uri("/rest/buku/status").retrieve().bodyToMono(String.class);
    };

    @Override
    public Mono<BukuDetail> postStatus() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", "Buku Cara Membaca Jilid 2");
        data.add("tahunTerbit", "2003");
        var response = this.webClient
            .post()
            .uri("/rest/buku/full-status")
            .bodyValue(data)
            .retrieve()
            .bodyToMono(BukuDetail.class);

        return response;
    }
}
