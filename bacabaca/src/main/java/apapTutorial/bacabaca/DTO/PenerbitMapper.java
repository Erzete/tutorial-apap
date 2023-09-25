package apapTutorial.bacabaca.DTO;

import apapTutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apapTutorial.bacabaca.model.Penerbit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PenerbitMapper {
    Penerbit createPenerbitRequestDTOToPenerbit(CreatePenerbitRequestDTO createPenerbitRequestDTO);
}
