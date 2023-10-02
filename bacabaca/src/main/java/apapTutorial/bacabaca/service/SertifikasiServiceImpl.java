package apapTutorial.bacabaca.service;

import apapTutorial.bacabaca.model.Penulis;
import apapTutorial.bacabaca.model.Sertifikasi;
import apapTutorial.bacabaca.repository.SertifikasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SertifikasiServiceImpl implements SertifikasiService{
    @Autowired
    SertifikasiDb sertifikasiDb;

    @Override
    public void createSertifikasi(Sertifikasi sertifikasi) {
        sertifikasiDb.save(sertifikasi);
    }

    @Override
    public List<Sertifikasi> getAllSertifikasi() {
        return sertifikasiDb.findAll();
    }

    @Override
    public void setIdPenulis(Sertifikasi sertifikasi, Penulis penulis) {
        sertifikasi.setPenulis(penulis);
        sertifikasiDb.save(sertifikasi);
    }
}
