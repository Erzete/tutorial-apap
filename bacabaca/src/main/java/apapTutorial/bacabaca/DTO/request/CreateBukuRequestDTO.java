package apapTutorial.bacabaca.DTO.request;

import apapTutorial.bacabaca.model.Penerbit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBukuRequestDTO {
    @NotBlank(message = "Judul tidak boleh kosong")
    @Size(max = 100, message = "Judul maksimal 100 karakter")
    private String judul;

    @NotBlank(message = "Tahun Terbit tidak boleh kosong")
    @Pattern(regexp = "\\d{4}", message = "Tahun Terbit haruslah berupa 4 digit nomor")
    private String tahunTerbit;

    @NotNull(message = "Harga tidak boleh kosong")
    private BigDecimal harga;

    @NotNull(message = "Penerbit tidak boleh kosong")
    private Penerbit penerbit;
}
// public class CreateBukuRequestDTO {
//     private UUID id;
//     private String judul;
//     private String penulis;
//     private String tahunTerbit;
//     private int harga;

//     public CreateBukuRequestDTO(){}

//     public CreateBukuRequestDTO(UUID id, String judul, String penulis, String tahunTerbit, int harga) {
//         this.id = id;
//         this.judul = judul;
//         this.penulis = penulis;
//         this.tahunTerbit = tahunTerbit;
//         this.harga = harga;
//     }

//     public UUID getId() {
//         return id;
//     }

//     public void setId(UUID id) {
//         this.id = id;
//     }

//     public String getJudul() {
//         return judul;
//     }

//     public void setJudul(String judul) {
//         this.judul = judul;
//     }

//     public String getPenulis() {
//         return penulis;
//     }

//     public void setPenulis(String penulis) {
//         this.penulis = penulis;
//     }

//     public String getTahunTerbit() {
//         return tahunTerbit;
//     }

//     public void setTahunTerbit(String tahunTerbit) {
//         this.tahunTerbit = tahunTerbit;
//     }

//     public int getHarga() {
//         return harga;
//     }

//     public void setHarga(int harga) {
//         this.harga = harga;
//     }

    
// }
