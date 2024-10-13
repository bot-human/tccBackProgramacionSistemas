package coreNetworking.tccBackProgramacionSistemas.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class GeneroDTO {

    @NotNull
    @Min(0)
    @Max(30)
    private String tipoGenero;

    public @NotNull @Min(0) @Max(30) String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(@NotNull @Min(0) @Max(30) String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
}
