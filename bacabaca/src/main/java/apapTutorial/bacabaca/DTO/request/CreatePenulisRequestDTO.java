package apapTutorial.bacabaca.DTO.request;

import java.util.List;

import apapTutorial.bacabaca.model.Sertifikasi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePenulisRequestDTO {
    private String namaPenulis;
    private String biografi;
    private List<Sertifikasi> listSertifikasi;
}
