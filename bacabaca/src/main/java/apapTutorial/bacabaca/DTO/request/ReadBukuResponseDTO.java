package apapTutorial.bacabaca.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import apapTutorial.bacabaca.model.Penulis;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBukuResponseDTO {
    private UUID id;
    private String judul;
    private String tahunTerbit;
    private BigDecimal harga;
    private String namaPenerbit;
    private List<Penulis> listPenulis;
}
