package apapTutorial.bacabaca.service;

import apapTutorial.bacabaca.model.Penerbit;
import java.util.List;

public interface PenerbitService {
    Penerbit createPenerbit(Penerbit penerbit);
    List<Penerbit> getAllPenerbit();
    Penerbit getPenerbitById(Long idPenerbit);
}
