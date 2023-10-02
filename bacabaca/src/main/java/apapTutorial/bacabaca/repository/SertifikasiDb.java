package apapTutorial.bacabaca.repository;

import apapTutorial.bacabaca.model.Sertifikasi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SertifikasiDb extends JpaRepository<Sertifikasi, Long> {
    List<Sertifikasi> findAll();
}
