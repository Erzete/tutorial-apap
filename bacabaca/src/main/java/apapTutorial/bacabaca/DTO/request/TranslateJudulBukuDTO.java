package apapTutorial.bacabaca.DTO.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TranslateJudulBukuDTO {
    String sourceLanguage;
    String targetLanguage;
    UUID bookId;
}
