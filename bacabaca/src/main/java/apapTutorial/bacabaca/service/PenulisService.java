package apapTutorial.bacabaca.service;

import apapTutorial.bacabaca.model.Penulis;
import java.util.List;

public interface PenulisService {
    void createPenulis(Penulis penulis);
    List<Penulis> getAllPenulis();
    void deleteListPenulis(List<Penulis> listPenulis);
}
