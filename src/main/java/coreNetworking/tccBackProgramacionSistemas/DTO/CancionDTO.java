package coreNetworking.tccBackProgramacionSistemas.DTO;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CancionDTO {

    @NotNull
    @Min(0)
    @Max(120)
    private String nombreCancion;
    @NotNull
    private int duracion;
    private Date fechaCreacion;
    private int busquedas;
    private int descargas;
    private List<Interprete> interpretes;
    private Genero genero;

    public @NotNull @Min(0) @Max(120) String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(@NotNull @Min(0) @Max(120) String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    @NotNull
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(@NotNull int duracion) {
        this.duracion = duracion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
