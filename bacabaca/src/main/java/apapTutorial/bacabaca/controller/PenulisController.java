package apapTutorial.bacabaca.controller;

import apapTutorial.bacabaca.DTO.request.CreatePenulisRequestDTO;
import apapTutorial.bacabaca.DTO.request.DeleteMultiplePenulisDTO;
import apapTutorial.bacabaca.model.Sertifikasi;
import apapTutorial.bacabaca.service.PenulisService;
import apapTutorial.bacabaca.service.SertifikasiService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apapTutorial.bacabaca.DTO.PenulisMapper;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PenulisController {
    @Autowired
    PenulisService penulisService;
    @Autowired
    PenulisMapper penulisMapper;
    @Autowired
    SertifikasiService sertifikasiService;

    @GetMapping("penulis/create")
    public String formAddPenulis(Model model) {
        var penulisDTO = new CreatePenulisRequestDTO();
        model.addAttribute("penulisDTO", penulisDTO);
        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"addRow"})
    public String addRowPenulisBuku(@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, Model model) {
        if(createPenulisRequestDTO.getListSertifikasi() == null || createPenulisRequestDTO.getListSertifikasi().size() == 0) {
            createPenulisRequestDTO.setListSertifikasi(new ArrayList<>());
        }

        createPenulisRequestDTO.getListSertifikasi().add(new Sertifikasi());

        model.addAttribute("penulisDTO", createPenulisRequestDTO);

        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"deleteRow"})
    public String deleteRowPenulisBuku(@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        if (createPenulisRequestDTO.getListSertifikasi() != null ) {
            createPenulisRequestDTO.getListSertifikasi().remove(row);
        }

        model.addAttribute("penulisDTO", createPenulisRequestDTO);

        return "form-create-penulis";
    }

    @PostMapping("penulis/create")
    public String addPenulis(@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, Model model) {
        var penulis = penulisMapper.createPenulisRequestDTOToPenulis(createPenulisRequestDTO);
        penulisService.createPenulis(penulis);
        

        for (Sertifikasi sertifikasi: penulis.getListSertifikasi()) {
            sertifikasiService.setIdPenulis(sertifikasi, penulis);
        }

        model.addAttribute("penulis", createPenulisRequestDTO);
        return "success-create-penulis";
    }

    @GetMapping("penulis/viewall")
    public String listPenulis(Model model) {
        var listPenulis = penulisService.getAllPenulis();
        var deleteDTO = new DeleteMultiplePenulisDTO();

        model.addAttribute("listPenulis", listPenulis);
        model.addAttribute("deleteDTO", deleteDTO);        
        return "viewall-penulis";
    }

    @PostMapping(value="penulis/delete")
    public String deleteMultiplePenulis(@ModelAttribute DeleteMultiplePenulisDTO deleteDTO) {
        penulisService.deleteListPenulis(deleteDTO.getListPenulis());
        return "success-delete-penulis";
    }
    
}
