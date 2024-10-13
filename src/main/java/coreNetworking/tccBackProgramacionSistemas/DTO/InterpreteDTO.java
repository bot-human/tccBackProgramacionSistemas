package coreNetworking.tccBackProgramacionSistemas.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor

public class InterpreteDTO {

    @NotNull
    @Min(0)
    @Max(120)
    private String nombre;

    private Date fechaCreacion;

    @Min(0)
    @Max(120)
    private String paisOrigen;

    public @NotNull @Min(0) @Max(120) String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull @Min(0) @Max(120) String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public @Min(0) @Max(120) String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(@Min(0) @Max(120) String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
}
