package coreNetworking.tccBackProgramacionSistemas.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name="interpretes")
public class Interprete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInterprete;

    @Column(nullable = false, length = 120,unique = true)
    private String nombre;
    @Column
    private Date fechaCreacion;
    @Column(length = 60)
    private String paisOrigen;

    @JsonIgnore
    @ManyToMany(mappedBy = "interpretes",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    List<Cancion> canciones=new ArrayList<>();

    public Interprete(int idInterprete,String nombre, Date fechaCreacion, String paisOrigen) {
        this.idInterprete=idInterprete;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.paisOrigen = paisOrigen;
    }
    public Interprete(String nombre,Date fechaCreacion, String paisOrigen){
        this.nombre =nombre;
        this.fechaCreacion=fechaCreacion;
        this.paisOrigen=paisOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
