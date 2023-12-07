package apapTutorial.bacabaca.model;
import java.util.UUID;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
@Where(clause = "is_deleted = false")
@JsonIgnoreProperties(value = {"listPenulis", "deleted"}, allowSetters = true)
public class Buku {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(max = 100)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @Size(max = 4)
    @Column(name = "tahun_terbit", nullable = false)
    private String tahunTerbit;
    
    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penerbit", referencedColumnName = "idPenerbit")
    private Penerbit penerbit;

    @ManyToMany
    @JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_penulis"))
    List<Penulis> listPenulis;
}
// public class Buku {
//     private UUID id;
//     private String judul;
//     private String penulis;
//     private String tahunTerbit;
//     private int harga;
    
//     public Buku(UUID id, String judul, String penulis, String tahunTerbit, int harga) {
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
