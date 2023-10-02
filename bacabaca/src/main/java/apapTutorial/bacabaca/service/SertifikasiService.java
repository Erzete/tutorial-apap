package apapTutorial.bacabaca.service;

import java.util.List;

import apapTutorial.bacabaca.model.Penulis;
import apapTutorial.bacabaca.model.Sertifikasi;
import org.springframework.stereotype.Service;

@Service
public interface SertifikasiService {
    void createSertifikasi(Sertifikasi sertifikasi);
    List<Sertifikasi> getAllSertifikasi();
    void setIdPenulis(Sertifikasi sertifikasi, Penulis penulis);
}
