package coreNetworking.tccBackProgramacionSistemas.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name="generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

    @Column(length = 60, nullable = false, unique = true)
    private String tipoGenero;


    @OneToMany(mappedBy = "genero",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},
            orphanRemoval = true)
    List<Cancion> canciones=new ArrayList<>();

    public Genero(int idGenero, String tipoGenero) {
        this.idGenero=idGenero;
        this.tipoGenero = tipoGenero;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
}
