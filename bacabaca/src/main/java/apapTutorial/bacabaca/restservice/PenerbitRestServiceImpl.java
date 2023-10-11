package apapTutorial.bacabaca.restservice;

import apapTutorial.bacabaca.model.Penerbit;
import apapTutorial.bacabaca.repository.PenerbitDb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PenerbitRestServiceImpl implements PenerbitRestService{
    @Autowired
    private PenerbitDb penerbitDb;

    @Override
    public List<Penerbit> retrieveRestAllPenerbit() {
        return penerbitDb.findAll();
    }

    @Override
    public void createRestPenerbit(Penerbit penerbit) {
        penerbitDb.save(penerbit);
    }

    @Override
    public Penerbit updateRestPenerbit(Penerbit penerbitFromDto) {
        Penerbit penerbit = getRestPenerbitById(penerbitFromDto.getIdPenerbit());
        if (penerbit != null) {
            penerbit.setAlamat(penerbitFromDto.getAlamat());
            penerbit.setEmail(penerbitFromDto.getEmail());
            penerbit.setNamaPenerbit(penerbitFromDto.getNamaPenerbit());
            penerbit.setListBuku(penerbitFromDto.getListBuku());
            penerbitDb.save(penerbit);
        }
        return penerbit;
    }

    @Override
    public Penerbit getRestPenerbitById(long id) {
        for (Penerbit penerbit : retrieveRestAllPenerbit()) {
            if (penerbit.getIdPenerbit() == id) {
                return penerbit;
            }
        }
        return null;
    }

    @Override
    public void deleteRestPenerbit(Penerbit penerbit) {
        penerbit.setDeleted(true);
        penerbitDb.save(penerbit);
    }
}
