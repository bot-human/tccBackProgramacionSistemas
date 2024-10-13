package coreNetworking.tccBackProgramacionSistemas.Servicio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;

public interface IGeneroServicio extends IServicio<Genero, Integer> {

    Genero findByTipoGenero(String tipoGenero);

    void borrarGeneroPorTipo(String tipoGenero);

    Genero save (Genero genero);
}
