package apapTutorial.bacabaca.service;

import java.util.List;

import apapTutorial.bacabaca.model.Penerbit;
import apapTutorial.bacabaca.repository.PenerbitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenerbitServiceImpl implements PenerbitService {
    @Autowired
    PenerbitDb penerbitDb;

    @Override
    public Penerbit createPenerbit(Penerbit penerbit) {
        return penerbitDb.save(penerbit);
    }

    @Override
    public List<Penerbit> getAllPenerbit() {
        return penerbitDb.findAll();
    }

    @Override
    public Penerbit getPenerbitById(Long idPenerbit) {
        return penerbitDb.findById(idPenerbit).get();
    }

}
