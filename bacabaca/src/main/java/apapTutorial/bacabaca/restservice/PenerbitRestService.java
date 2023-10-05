package apapTutorial.bacabaca.restservice;

import apapTutorial.bacabaca.model.Penerbit;

import java.util.List;

public interface PenerbitRestService {
    List<Penerbit> retrieveRestAllPenerbit();
    void createRestPenerbit(Penerbit penerbit);
    Penerbit updateRestPenerbit(Penerbit penerbit);
    Penerbit getRestPenerbitById(long idPenerbit);
    void deleteRestPenerbit(Penerbit penerbit);
}
