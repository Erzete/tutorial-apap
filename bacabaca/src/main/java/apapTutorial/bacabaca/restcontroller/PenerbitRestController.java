package apapTutorial.bacabaca.restcontroller;

import apapTutorial.bacabaca.DTO.PenerbitMapper;
import apapTutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apapTutorial.bacabaca.DTO.request.UpdatePenerbitRequestDTO;
import apapTutorial.bacabaca.model.Penerbit;
import apapTutorial.bacabaca.restservice.PenerbitRestService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class PenerbitRestController {
    @Autowired
    private PenerbitRestService penerbitRestService;
    
    @Autowired
    private PenerbitMapper penerbitMapper;

    @GetMapping(value = "/penerbit/view-all")
    private List<Penerbit> retrieveAllPenerbit() {
        return penerbitRestService.retrieveRestAllPenerbit();
    }

    @PutMapping(value="/penerbit/{idPenerbit}")
    public Penerbit updateRestPenerbit(@PathVariable("idPenerbit") long id, @RequestBody UpdatePenerbitRequestDTO updatePenerbitRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            var penerbit = penerbitMapper.updatePenerbitRequestDTOToPenerbit(updatePenerbitRequestDTO);
            penerbitRestService.updateRestPenerbit(penerbit);
            return penerbit;
        }        
    }

    @PostMapping(value="/penerbit/create")
    public Penerbit restAddPenerbit(@Valid @RequestBody CreatePenerbitRequestDTO penerbitDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(penerbitDTO);
            penerbitRestService.createRestPenerbit(penerbit);
            return penerbit;
        }
    }

    @GetMapping(value="/penerbit/{idPenerbit}")
    public Penerbit retrieveBuku(@PathVariable("idPenerbit") long id) {
        try {
            return penerbitRestService.getRestPenerbitById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Penerbit " + id + " not found");
        }
    }

    @DeleteMapping(value = "/penerbit/{idPenerbit}")
    public String deleteRestPenerbit(@PathVariable("idPenerbit") long id) {
        try {
            Penerbit penerbit = penerbitRestService.getRestPenerbitById(id);
            penerbitRestService.deleteRestPenerbit(penerbit);
            return "Penerbit has been deleted";
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Penerbit " + id + " not found");
        }
    }
}
