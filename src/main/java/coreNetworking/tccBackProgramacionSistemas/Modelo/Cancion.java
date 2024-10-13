package coreNetworking.tccBackProgramacionSistemas.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCancion;

    @Column(length = 120, nullable = false, unique = true)
    private String nombreCancion;
    @Column(nullable = false)
    private Date fechaCreacion;
    @Column(nullable = false)
    private int duracion;
    @Column(nullable = false)
    private int busquedas;
    @Column(nullable = false)
    private int descargas;

    @ManyToMany
    @JoinTable(
            name = "cancion_interprete",
            joinColumns = @JoinColumn(name = "id_cancion", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_Interprete")
    )
    private List<Interprete> interpretes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;

    public Cancion(String nombre, Date fechaCreacion, int duracion, int descargas, int busquedas, List<Interprete> interpretes, Genero genero) {
        this.nombreCancion = nombre;
        this.fechaCreacion = fechaCreacion;
        this.duracion = duracion;
        this.descargas = descargas;
        this.busquedas = busquedas;
        this.interpretes = interpretes;
        this.genero = genero;
    }

    public Cancion(String nombre, int duracion) {
        this.nombreCancion = nombre;
        this.duracion = duracion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getBusquedas() {
        return busquedas;
    }

    public void setBusquedas(int busquedas) {
        this.busquedas = busquedas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Interprete> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(List<Interprete> interpretes) {
        this.interpretes = interpretes;
    }
}