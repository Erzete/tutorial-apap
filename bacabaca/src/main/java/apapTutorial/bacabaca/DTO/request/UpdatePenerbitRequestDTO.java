package apapTutorial.bacabaca.DTO.request;

import java.util.List;

import apapTutorial.bacabaca.model.Buku;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePenerbitRequestDTO extends CreatePenerbitRequestDTO {
    private long idPenerbit;  
    private List<Buku> listBuku;
}
