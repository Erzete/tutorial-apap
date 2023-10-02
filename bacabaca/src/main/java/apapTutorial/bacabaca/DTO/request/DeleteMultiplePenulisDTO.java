package apapTutorial.bacabaca.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import apapTutorial.bacabaca.model.Penulis;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteMultiplePenulisDTO {
    private List<Penulis> listPenulis;
}
