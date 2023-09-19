package apapTutorial.bacabaca.controller;

import apapTutorial.bacabaca.controller.DTO.BukuDTO;
import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class BukuController {
    @Autowired
    private BukuService bukuService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        var bukuDTO = new BukuDTO();

        model.addAttribute("bukuDTO", bukuDTO);

        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku (@ModelAttribute BukuDTO bukuDTO, Model model) {
        if (!bukuService.adaBuku(bukuDTO.getJudul())) {
            UUID newId = UUID.randomUUID();

            var buku = new Buku(newId, bukuDTO.getJudul(), bukuDTO.getPenulis(), bukuDTO.getTahunTerbit(), bukuDTO.getHarga());

            bukuService.createBuku(buku);

            model.addAttribute("id", buku.getId());

            model.addAttribute("judul", buku.getJudul());

            return "success-create-buku";
        }
        else {
            model.addAttribute("judul", bukuDTO.getJudul());

            return "unsuccessful";
        }
    }

    @GetMapping("buku/{id}/update")
    public String formUbahBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        var bukuDTO = new BukuDTO(buku.getId(), buku.getJudul(), buku.getPenulis(), buku.getTahunTerbit(), buku.getHarga());

        model.addAttribute("bukuDTO", bukuDTO);

        return "form-update-buku";
    }

    @PostMapping("buku/update")
    public String ubahBuku (@ModelAttribute BukuDTO bukuDTO, Model model) {
        if (!bukuService.adaBukuUpdate(bukuDTO.getJudul(), bukuDTO.getId())) {
            var buku = bukuService.getBukuById(bukuDTO.getId());

            buku.setJudul(bukuDTO.getJudul());
            buku.setPenulis(bukuDTO.getPenulis());
            buku.setTahunTerbit(bukuDTO.getTahunTerbit());
            buku.setHarga(bukuDTO.getHarga());

            model.addAttribute("id", bukuDTO.getId());

            return "success-update-buku";
        }
        else {
            model.addAttribute("judul", bukuDTO.getJudul());

            return "unsuccessful";
        }
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model) {
        List<Buku> listBuku = bukuService.getAllBuku();

        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    //@GetMapping("buku")
    //public String detailBuku(@RequestParam("id") UUID id, Model model) {

        //var buku = bukuService.getBukuById(id);

        //model.addAttribute("buku", buku);
        //return "view-buku";
    //}

    @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable("id") UUID id, Model model) {

        var buku = bukuService.getBukuById(id);

        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @GetMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {

        var buku = bukuService.getBukuById(id);
        bukuService.deleteBuku(buku);

        model.addAttribute("id", buku.getId());
        return "delete-buku";
    }

    @GetMapping("buku/deleteAll")
    public String deleteBuku(@RequestParam("tahunTerbit") String tahunTerbit, Model model) {
        List<Buku> listBuku = bukuService.getAllBuku();

        for (int i = listBuku.size() - 1; i >= 0; i--) {
            if (listBuku.get(i).getTahunTerbit().equals(tahunTerbit)) {
                listBuku.remove(i);
                continue;
            }
        }       

        model.addAttribute("tahunTerbit", tahunTerbit);
        return "delete-tahun";
    }
}
