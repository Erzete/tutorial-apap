package apapTutorial.bacabaca.controller;

import apapTutorial.bacabaca.DTO.BukuMapper;
import apapTutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apapTutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apapTutorial.bacabaca.DTO.request.ReadBukuResponseDTO;
import apapTutorial.bacabaca.model.Buku;
import apapTutorial.bacabaca.repository.BukuDb;
import apapTutorial.bacabaca.service.BukuService;
import apapTutorial.bacabaca.service.PenerbitService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

@Controller
public class BukuController {
    @Autowired
    private BukuMapper bukuMapper;
    @Autowired
    private BukuService bukuService;
    @Autowired
    private PenerbitService penerbitService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        var bukuDTO = new CreateBukuRequestDTO();

        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
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
    public String listBuku(@RequestParam(name="judul", defaultValue = "") String judul, @RequestParam(name="hargaAtas", defaultValue = "9223372036854775807") BigDecimal hargaAtas, @RequestParam(name="hargaBawah", defaultValue = "0") BigDecimal hargaBawah,  Model model) {
        List<Buku> listBuku;
        
        listBuku = bukuService.findBukuByJudulAndHarga(judul, hargaBawah, hargaAtas);

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
