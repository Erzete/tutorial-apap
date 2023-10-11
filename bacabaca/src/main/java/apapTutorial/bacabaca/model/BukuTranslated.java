package apapTutorial.bacabaca.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BukuTranslated {
    private UUID id;
    private String judul;
    private String judulLower;
    private String tahunTerbit;
    private BigDecimal harga;
    List<Penulis> listPenulis;
    private boolean deleted;
}