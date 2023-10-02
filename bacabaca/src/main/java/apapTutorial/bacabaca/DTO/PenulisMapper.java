package apapTutorial.bacabaca.DTO;

import apapTutorial.bacabaca.DTO.request.CreatePenulisRequestDTO;
import apapTutorial.bacabaca.model.Penulis;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PenulisMapper {
    Penulis createPenulisRequestDTOToPenulis(CreatePenulisRequestDTO createPenulisRequestDTO);
}
