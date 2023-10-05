package apapTutorial.bacabaca.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BukuServiceImpl implements BukuService {
    @Autowired
    BukuDb bukuDb;

    @Override
    public void saveBuku(Buku buku) {
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return bukuDb.findAll();
    }

    @Override
    public Buku getBukuById(UUID kodeBuku) {
        for (Buku buku : getAllBuku()) {
            if (buku.getId().equals(kodeBuku)) {
                return buku;
            }
        }
        return null;
    }

    @Override
    public Buku updateBuku(Buku bukuFromDto) {
        Buku buku = getBukuById(bukuFromDto.getId());
        if (buku != null) {
            buku.setHarga(bukuFromDto.getHarga());
            buku.setJudul(bukuFromDto.getJudul());
            buku.setListPenulis(bukuFromDto.getListPenulis());
            buku.setTahunTerbit(bukuFromDto.getTahunTerbit());
            buku.setPenerbit(bukuFromDto.getPenerbit());
            bukuDb.save(buku);
        }
        return buku;
    }

    @Override
    public boolean isJudulExist(String judul) {
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul));
    }

    @Override
    public boolean isJudulExist(String judul, UUID id) {
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul) && !b.getId().equals(id));
    }

    @Override
    public void deleteBuku(Buku buku) {
        buku.setDeleted(true);
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> findBukuByJudul(String judul) {
        return bukuDb.findByJudulContainingIgnoreCaseOrderByJudul(judul);
    }

    @Override
    public Map<String, Float> getMonthPopularBook() {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH)+1;
        int currentYear = cal.get(Calendar.YEAR);
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://hapi-books.p.rapidapi.com/month/" + currentYear + "/" + currentMonth))
		.header("X-RapidAPI-Key", "d46c6a4291msh3cc8e7a29dacc40p11e911jsnf0c85c08f244")
		.header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
        String json = response.body(); // Assuming the JSON response is stored in the 'response' variable

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Float> thisMonthBookRating = new HashMap<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            for (JsonNode bookNode : root) {
                String name = bookNode.get("name").asText();
                float rating = bookNode.get("rating").floatValue();
                
                thisMonthBookRating.put(name, rating);
            }
            return thisMonthBookRating;
        } catch (IOException e) {
            return null;
        }
    }
}
// @Service
// public class BukuServiceImpl implements BukuService {
//     private List<Buku> listBuku;

//     public BukuServiceImpl() {
//         listBuku = new ArrayList<>();
//     }

//     @Override
//     public void createBuku(Buku buku) {
//         listBuku.add(buku);
//     }

//     @Override
//     public List<Buku> getAllBuku() {
//         return listBuku;
//     }

//     @Override
//     public Buku getBukuById(UUID id) {
//         for (Buku i : listBuku) {
//             if (i.getId().equals(id)) {
//                 return i;
//             }
//         }
//         return null;
//     }

//     @Override
//     public void deleteBuku(Buku buku) {
//         listBuku.remove(buku);
//     }

//     @Override
//     public boolean adaBuku(String judul) {
//         for (Buku i : listBuku) {
//             if (i.getJudul().equalsIgnoreCase(judul)) {
//                 return true;
//             }
//         }
//         return false;        
//     }

//     @Override
//     public boolean adaBukuUpdate(String judul, UUID id) {
//         for (Buku i : listBuku) {
//             if (i.getJudul().equalsIgnoreCase(judul) && !i.getId().equals(id)) {
//                 return true;
//             }
//         }
//         return false;        
//     }
// }
