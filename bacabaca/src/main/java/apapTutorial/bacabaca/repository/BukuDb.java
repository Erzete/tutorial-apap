package apapTutorial.bacabaca.repository;

import apapTutorial.bacabaca.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.math.BigDecimal;


@Repository
public interface BukuDb extends JpaRepository<Buku, UUID> {
    List<Buku> findByJudulContainingIgnoreCaseOrderByJudul(String infix);
    List<Buku> findByHargaBetween(BigDecimal hargaBawah, BigDecimal hargaAtas);
    List<Buku> findByJudulContainingIgnoreCaseAndHargaBetweenOrderByJudul(String infix, BigDecimal hargaBawah, BigDecimal hargaAtas);
}
