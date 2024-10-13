package coreNetworking.tccBackProgramacionSistemas.Repositorio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IGeneroRepository extends IRepository<Genero, Integer>{

    @Query("SELECT g FROM Genero g WHERE g.tipoGenero = :tipoGenero")
    Genero findByTipoGenero(@Param("tipoGenero") String tipoGenero);

//    @Query(value = "SELECT * FROM generos WHERE id_genero = :id",
//            nativeQuery = true)
//    List<Genero> findByGeneroPorId(@Param("id")int idGenero);
//
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM generos WHERE id_genero = :id",
//            nativeQuery = true)
//    void borrarGeneroPorId(@Param("id") int idGenero);


    @Modifying
    @Transactional
    @Query(value ="DELETE FROM Genero WHERE tipoGenero=:tipoGenero",
            nativeQuery = true)
    void borrarGeneroPorTipo(@Param("tipoGenero")String tipoGenero);

}
