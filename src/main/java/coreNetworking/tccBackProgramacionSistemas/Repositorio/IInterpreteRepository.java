package coreNetworking.tccBackProgramacionSistemas.Repositorio;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IInterpreteRepository extends IRepository<Interprete, Integer> {

    @Query("SELECT i FROM Interprete i WHERE i.nombre = :nombre")
    Interprete findByInterpretePorNombre(@Param("nombre")String nombre);


//    @Query(value = "SELECT * FROM interpretes WHERE id_interprete = :id",nativeQuery = true)
//    List<Interprete> findByInterpretePorId(@Param("id")int idInterprete);
//
//
//    @Modifying
//    @Transactional
//    @Query(value ="DELETE FROM cancion_interprete WHERE id_interprete=:id",
//            nativeQuery = true)
//    void borrarInterpretePorId(@Param("id") int id_interprete);

    @Modifying
    @Transactional
    @Query("DELETE FROM Interprete i WHERE i.nombre = :nombre")
    void borrarInterpretePorNombre(@Param("nombre")String nombre);


}

