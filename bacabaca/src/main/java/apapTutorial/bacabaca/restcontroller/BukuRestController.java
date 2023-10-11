package apapTutorial.bacabaca.restcontroller;

import apapTutorial.bacabaca.DTO.BukuMapper;
import apapTutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apapTutorial.bacabaca.DTO.request.TranslateJudulBukuDTO;
import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.model.BukuTranslated;
import apapTutorial.bacabaca.rest.BukuDetail;
import apapTutorial.bacabaca.restservice.BukuRestService;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class BukuRestController {
    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuRestService bukuRestService;

    @GetMapping("/buku/view-all")
    private List<Buku> retrieveAllBuku() {
        return bukuRestService.retrieveRestAllBuku();
    }

    @GetMapping(value="/buku/{id}")
    public Buku retrieveBuku(@PathVariable("id") String id) {
        try {
            return bukuRestService.getRestBukuById(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Buku " + id + " not found");
        }
    }
    
    @PostMapping(value="/buku/create")
    public Buku restAddBuku(@Valid @RequestBody CreateBukuRequestDTO bukuDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.createRestBuku(buku);
            return buku;
        }
    }
    
    @GetMapping(value = "/buku/status")
    private Mono<String> getStatus() {
        return bukuRestService.getStatus();
    }

    @PostMapping(value = "/full")
    private Mono<BukuDetail> postStatus() {
        return bukuRestService.postStatus();
    }

    @PostMapping(value = "/buku/translate")
    private BukuTranslated translateBuku(@RequestBody TranslateJudulBukuDTO bukuDTO) {
        var buku = bukuRestService.getRestBukuById(bukuDTO.getBookId());

        String url = "https://text-translator2.p.rapidapi.com/translate";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-RapidAPI-Key", "d46c6a4291msh3cc8e7a29dacc40p11e911jsnf0c85c08f244");
        headers.set("X-RapidAPI-Host", "text-translator2.p.rapidapi.com");

        String requestBody = "source_language=" + bukuDTO.getSourceLanguage() + "&target_language=" + bukuDTO.getTargetLanguage() + "&text=" + buku.getJudul();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            String translatedText = responseJson.get("data").get("translatedText").asText();
            BukuTranslated bukuTranslated = new BukuTranslated();
            bukuTranslated.setId(buku.getId());
            bukuTranslated.setJudul(buku.getJudul());
            bukuTranslated.setHarga(buku.getHarga());
            bukuTranslated.setJudulLower(translatedText);
            bukuTranslated.setListPenulis(buku.getListPenulis());
            bukuTranslated.setTahunTerbit(buku.getTahunTerbit());
            bukuTranslated.setDeleted(buku.isDeleted());
            return bukuTranslated;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
    }
}
