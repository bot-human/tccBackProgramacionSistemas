package coreNetworking.tccBackProgramacionSistemas.Repositorio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICancionRepository extends IRepository<Cancion, Integer> {

    @Query("SELECT c FROM Cancion c WHERE c.nombreCancion = :nombreCancion")
    Cancion findByCancionesPorNombre(@Param("nombreCancion")String nombreCancion);


//    @Query(value = "SELECT * FROM Cancion WHERE id_genero = :name",nativeQuery = true)
//    List<Cancion> findByCancionPorId(@Param("name")int idGenero);
//
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM cancion_interprete WHERE id_cancion = :id",
//            nativeQuery = true)
//    void borrarCancionPorId(@Param("id") int idCancion);


    @Modifying
    @Transactional
    @Query(value ="DELETE FROM Cancion WHERE nombreCancion=:nombreCancion",nativeQuery = true)
    void borrarCancionPorNombre(@Param("nombreCancion")String nombreCancion);

}

