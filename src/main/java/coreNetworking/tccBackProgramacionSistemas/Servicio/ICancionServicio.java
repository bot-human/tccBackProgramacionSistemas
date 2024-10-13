package coreNetworking.tccBackProgramacionSistemas.Servicio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;

public interface ICancionServicio extends IServicio<Cancion, Integer> {

    Cancion findByCancionesPorNombre(String nombreCancion); //no puedo llamar lista de Cancion al implementar el metodo que el get devolve 1

    void borrarCancionPorNombre(String nombreCancion);

    Cancion save(Cancion cancion);

}
