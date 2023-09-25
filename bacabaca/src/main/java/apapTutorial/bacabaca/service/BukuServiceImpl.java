package apapTutorial.bacabaca.service;

import java.util.List;
import java.util.UUID;

import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BukuServiceImpl implements BukuService {
    @Autowired
    BukuDb bukuDb;

    @Override
    public void saveBuku(Buku buku) {
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return bukuDb.findAll();
    }

    @Override
    public Buku getBukuById(UUID kodeBuku) {
        for (Buku buku : getAllBuku()) {
            if (buku.getId().equals(kodeBuku)) {
                return buku;
            }
        }
        return null;
    }

    @Override
    public Buku updateBuku(Buku bukuFromDto) {
        Buku buku = getBukuById(bukuFromDto.getId());
        if (buku != null) {
            buku.setHarga(bukuFromDto.getHarga());
            buku.setJudul(bukuFromDto.getJudul());
            buku.setListPenulis(bukuFromDto.getListPenulis());
            buku.setTahunTerbit(bukuFromDto.getTahunTerbit());
            buku.setPenerbit(bukuFromDto.getPenerbit());
            bukuDb.save(buku);
        }
        return buku;
    }

    @Override
    public boolean isJudulExist(String judul) {
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul));
    }

    @Override
    public boolean isJudulExist(String judul, UUID id) {
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul) && !b.getId().equals(id));
    }

    @Override
    public void deleteBuku(Buku buku) {
        buku.setDeleted(true);
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> findBukuByJudul(String judul) {
        return bukuDb.findByJudulContainingIgnoreCaseOrderByJudul(judul);
    }
}
// @Service
// public class BukuServiceImpl implements BukuService {
//     private List<Buku> listBuku;

//     public BukuServiceImpl() {
//         listBuku = new ArrayList<>();
//     }

//     @Override
//     public void createBuku(Buku buku) {
//         listBuku.add(buku);
//     }

//     @Override
//     public List<Buku> getAllBuku() {
//         return listBuku;
//     }

//     @Override
//     public Buku getBukuById(UUID id) {
//         for (Buku i : listBuku) {
//             if (i.getId().equals(id)) {
//                 return i;
//             }
//         }
//         return null;
//     }

//     @Override
//     public void deleteBuku(Buku buku) {
//         listBuku.remove(buku);
//     }

//     @Override
//     public boolean adaBuku(String judul) {
//         for (Buku i : listBuku) {
//             if (i.getJudul().equalsIgnoreCase(judul)) {
//                 return true;
//             }
//         }
//         return false;        
//     }

//     @Override
//     public boolean adaBukuUpdate(String judul, UUID id) {
//         for (Buku i : listBuku) {
//             if (i.getJudul().equalsIgnoreCase(judul) && !i.getId().equals(id)) {
//                 return true;
//             }
//         }
//         return false;        
//     }
// }
