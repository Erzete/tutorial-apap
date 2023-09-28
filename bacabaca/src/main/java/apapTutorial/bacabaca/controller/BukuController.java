package apapTutorial.bacabaca.controller;

import apapTutorial.bacabaca.DTO.BukuMapper;
import apapTutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apapTutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.model.Penulis;
import apapTutorial.bacabaca.service.BukuService;
import apapTutorial.bacabaca.service.PenerbitService;
import apapTutorial.bacabaca.service.PenulisService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class BukuController {
    @Autowired
    private BukuMapper bukuMapper;
    @Autowired
    private BukuService bukuService;
    @Autowired
    private PenerbitService penerbitService;
    @Autowired
    private PenulisService penulisService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        var bukuDTO = new CreateBukuRequestDTO();

        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());

        return "form-create-buku";
    }

    @PostMapping(value = "buku/create", params = {"addRow"})
    public String addRowPenulisBuku(@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, Model model) {
        if(createBukuRequestDTO.getListPenulis() == null || createBukuRequestDTO.getListPenulis().size() == 0) {
            createBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        createBukuRequestDTO.getListPenulis().add(new Penulis());

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", createBukuRequestDTO);

        return "form-create-buku";
    }

    @PostMapping(value = "buku/create", params = {"deleteRow"})
    public String deleteRowPenulisBuku(@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        if (createBukuRequestDTO.getListPenulis() != null) {
            createBukuRequestDTO.getListPenulis().remove(row);
        }

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", createBukuRequestDTO);

        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku (@Valid @ModelAttribute CreateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage() + ", ");
            }
            model.addAttribute("errorMessage", errorMessage.toString());
            return "error-view"; 
        }

        if (bukuService.isJudulExist(bukuDTO.getJudul())) {
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
        var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
        bukuService.saveBuku(buku);

        model.addAttribute("id", buku.getId());
        model.addAttribute("judul", buku.getJudul());
        return "success-create-buku";
    }

    @GetMapping("buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        var bukuDTO = bukuMapper.bukuToUpdateBukuRequestDTO(buku);
        
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", bukuDTO);

        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"addRow"})
    public String addRowUpdateBuku(@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, Model model) {
        if(updateBukuRequestDTO.getListPenulis() == null || updateBukuRequestDTO.getListPenulis().size() == 0) {
            updateBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        updateBukuRequestDTO.getListPenulis().add(new Penulis());

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", updateBukuRequestDTO);

        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"deleteRow"})
    public String deleteRowUpdateBuku(@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        if (updateBukuRequestDTO.getListPenulis() != null) {
            updateBukuRequestDTO.getListPenulis().remove(row);
        }

        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", updateBukuRequestDTO);

        return "form-update-buku";
    }

    @PostMapping("buku/update")
    public String updateBuku (@Valid @ModelAttribute UpdateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage() + ", ");
            }
            model.addAttribute("errorMessage", errorMessage.toString());
            return "error-view"; 
        }

        if (bukuService.isJudulExist(bukuDTO.getJudul(), bukuDTO.getId())) {
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        
        var bukuFromDto = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);
        var buku = bukuService.updateBuku(bukuFromDto);

        model.addAttribute("id", buku.getId());
        model.addAttribute("judul", buku.getJudul());
        return "success-update-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(@RequestParam(name="judul", defaultValue = "") String judul, Model model) {
        List<Buku> listBuku;
        listBuku = bukuService.findBukuByJudul(judul);

        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku/viewall-with-datatables")
    public String listBukuDatables(@RequestParam(name="judul", defaultValue = "") String judul, Model model) {
        List<Buku> listBuku;
        listBuku = bukuService.findBukuByJudul(judul);

        model.addAttribute("listBuku", listBuku);
        return "viewall-buku-with-datatables";
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

        var bukuDTO = bukuMapper.bukuToReadBukuResponseDTO(buku);

        model.addAttribute("buku", bukuDTO);
        return "view-buku";
    }

    @GetMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {

        var buku = bukuService.getBukuById(id);
        bukuService.deleteBuku(buku);

        model.addAttribute("id", id);
        return "delete-buku";
    }
}
