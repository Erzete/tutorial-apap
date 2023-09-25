package apapTutorial.bacabaca.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBukuRequestDTO  extends CreateBukuRequestDTO{
    @NotNull(message = "ID tidak boleh kosong")
    private UUID id;  
}
