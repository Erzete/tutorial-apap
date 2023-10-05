package apapTutorial.bacabaca.DTO;

import apapTutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apapTutorial.bacabaca.DTO.request.UpdatePenerbitRequestDTO;
import apapTutorial.bacabaca.model.Penerbit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PenerbitMapper {
    Penerbit createPenerbitRequestDTOToPenerbit(CreatePenerbitRequestDTO createPenerbitRequestDTO);
    Penerbit updatePenerbitRequestDTOToPenerbit(UpdatePenerbitRequestDTO updatePenerbitRequestDTO);
    UpdatePenerbitRequestDTO penerbitToUpdatePenerbitRequestDTO(Penerbit penerbit);
}
