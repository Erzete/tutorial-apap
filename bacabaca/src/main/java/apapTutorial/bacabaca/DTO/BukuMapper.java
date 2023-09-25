package apapTutorial.bacabaca.DTO;

import apapTutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apapTutorial.bacabaca.DTO.request.ReadBukuResponseDTO;
import apapTutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apapTutorial.bacabaca.model.Buku;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BukuMapper {
    Buku createBukuRequestDTOToBuku(CreateBukuRequestDTO createBukuRequestDTO);

    Buku updateBukuRequestDTOToBuku(UpdateBukuRequestDTO updateBukuRequestDTO);

    UpdateBukuRequestDTO bukuToUpdateBukuRequestDTO(Buku buku);

    ReadBukuResponseDTO bukuToReadBukuResponseDTO(Buku buku);

    @AfterMapping
    default void ambilNamaPenerbit(@MappingTarget ReadBukuResponseDTO readBukuResponseDTO, Buku buku) {
        readBukuResponseDTO.setNamaPenerbit(buku.getPenerbit().getNamaPenerbit());
    }
}
