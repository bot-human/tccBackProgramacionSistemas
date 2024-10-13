package coreNetworking.tccBackProgramacionSistemas.Servicio.Implementacion;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.IRepository;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.IGeneroRepository;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IGeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicioImpl extends ServicioImp<Genero, Integer> implements IGeneroServicio {

    @Autowired
    private IGeneroRepository GeneroRepositorio;

    @Override
    protected IRepository<Genero, Integer> getRepo() {
        return GeneroRepositorio;
    }

    @Override
    public Genero findByTipoGenero(String tipoGenero) {
        Genero generos = GeneroRepositorio.findByTipoGenero(tipoGenero);
        if (generos==null){
            return null;
        } else {
            return generos;
        }
    }

    @Override
    public void borrarGeneroPorTipo(String tipoGenero) {
        Genero genero=GeneroRepositorio.findByTipoGenero(tipoGenero);
        GeneroRepositorio.borrarGeneroPorTipo(genero.getTipoGenero());
    }

    @Override
    public Genero save(Genero genero) {
        return GeneroRepositorio.save(genero);
    }

}
