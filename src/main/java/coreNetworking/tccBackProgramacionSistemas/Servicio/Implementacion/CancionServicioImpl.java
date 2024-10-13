package coreNetworking.tccBackProgramacionSistemas.Servicio.Implementacion;


import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.IRepository;
import coreNetworking.tccBackProgramacionSistemas.Repositorio.ICancionRepository;
import coreNetworking.tccBackProgramacionSistemas.Servicio.ICancionServicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Service
public class CancionServicioImpl extends ServicioImp<Cancion, Integer> implements ICancionServicio {

    @Autowired
    private ICancionRepository CancionRepositorio;

    @Override
    protected IRepository<Cancion, Integer> getRepo() {
        return CancionRepositorio;
    }

    @Override
    public Cancion findByCancionesPorNombre(String nombreCancion) {
        Cancion canciones=CancionRepositorio.findByCancionesPorNombre(nombreCancion);
        if (canciones==null){
            return null;
        }else {
            return canciones;
        }
    }

    @Override
    public void borrarCancionPorNombre(String nombreCancion) {
        Cancion cancion=CancionRepositorio.findByCancionesPorNombre(nombreCancion);
        CancionRepositorio.borrarCancionPorNombre(cancion.getNombreCancion());
    }

    @Override
    public Cancion save(Cancion cancion) {
        return CancionRepositorio.save(cancion);
    }


//    public Cancion incrementoDescarga(Cancion cancion){
//        cancion.setDescargas(cancion.getDescargas()+1);
//        iCrudCancionRepositorio.save(cancion);
//        return cancion;
//    }
//    public Cancion incrementoVenta(Cancion cancion){
//        cancion.setDescargas(cancion.getDescargas()+1);
//        iCrudCancionRepositorio.save(cancion);
//        return cancion;
//    }
//    public Cancion incrementoBusqueda(Cancion cancion){
//        cancion.setBusquedas(cancion.getBusquedas()+1);
//        iCrudCancionRepositorio.save(cancion);
//        return cancion;
//    }
}
