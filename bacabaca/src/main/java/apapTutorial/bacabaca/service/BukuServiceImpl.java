package apapTutorial.bacabaca.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import apapTutorial.bacabaca.model.Buku;
import org.springframework.stereotype.Service;

@Service
public class BukuServiceImpl implements BukuService {
    private List<Buku> listBuku;

    public BukuServiceImpl() {
        listBuku = new ArrayList<>();
    }

    @Override
    public void createBuku(Buku buku) {
        listBuku.add(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return listBuku;
    }

    @Override
    public Buku getBukuById(UUID id) {
        for (Buku i : listBuku) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void deleteBuku(Buku buku) {
        listBuku.remove(buku);
    }

    @Override
    public boolean adaBuku(String judul) {
        for (Buku i : listBuku) {
            if (i.getJudul().equalsIgnoreCase(judul)) {
                return true;
            }
        }
        return false;        
    }

    @Override
    public boolean adaBukuUpdate(String judul, UUID id) {
        for (Buku i : listBuku) {
            if (i.getJudul().equalsIgnoreCase(judul) && !i.getId().equals(id)) {
                return true;
            }
        }
        return false;        
    }
}
