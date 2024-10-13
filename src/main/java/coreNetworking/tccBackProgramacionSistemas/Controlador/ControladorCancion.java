package coreNetworking.tccBackProgramacionSistemas.Controlador;

import coreNetworking.tccBackProgramacionSistemas.DTO.CancionDTO;
import coreNetworking.tccBackProgramacionSistemas.Excepciones.ExcepcionNoEncontradoModelo;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import coreNetworking.tccBackProgramacionSistemas.Servicio.ICancionServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/canciones")
public class ControladorCancion {

    @Autowired
    private ICancionServicio cancionServicio;

    @Autowired
    private ModelMapper mapper;


    @GetMapping //OBTENER TODAS
    public ResponseEntity<List<CancionDTO>> consultarTodasLasCanciones() throws Exception {
        List<CancionDTO> TodasLasC = cancionServicio.listar()
                .stream().map(x -> mapper.map(x, CancionDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(TodasLasC, HttpStatus.OK);
    }

    @GetMapping("nombreCancion") //OBTENER UNA CANCION POR NOMBRE
    public ResponseEntity<CancionDTO> consultarCancionPorNombre(@PathVariable("nombreCancion") String nombreCancion) throws Exception {
        Cancion cancionBBDD = cancionServicio.findByCancionesPorNombre(nombreCancion);
        if (cancionBBDD == null) {
            throw new ExcepcionNoEncontradoModelo(nombreCancion + " no ha sido encontrada");
        }
        return new ResponseEntity<>(mapper.map(cancionServicio.findByCancionesPorNombre(nombreCancion),
                CancionDTO.class), HttpStatus.OK);
    }


    @GetMapping("/{id}") //OBTENER UNA CANCION POR ID
    public ResponseEntity<CancionDTO> consultarCancionPorId(@PathVariable("id") Integer idCancion) throws Exception {
        Cancion cancionPorId = cancionServicio.listarPorId(idCancion);
        if (cancionPorId == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CancionDTO cancionDto = mapper.map(cancionPorId, CancionDTO.class);
        return new ResponseEntity<>(cancionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarCancion(@Validated @RequestBody CancionDTO cancionNuevaDto) throws Exception{
        // Mapear DTO a entidad
        Cancion cancionNueva = mapper.map(cancionNuevaDto, Cancion.class);

        // Validación de nombreCancion
        if (cancionNueva.getNombreCancion() == null || cancionNueva.getNombreCancion().isEmpty()) {
            return new ResponseEntity<>("El campo 'nombreCancion' no puede ser nulo o vacío.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si ya existe una canción con el mismo nombre
        Cancion cancionExistente = cancionServicio.findByCancionesPorNombre(cancionNueva.getNombreCancion());
        if (cancionExistente != null) {
            // Si existe, devolver CONFLICT
            return new ResponseEntity<>("Ya existe una canción con este nombre.", HttpStatus.CONFLICT);
        }

        // Guardar la nueva canción en la base de datos
        Cancion cancionGuardada = cancionServicio.registrar(cancionNueva);

        // Mapear la entidad guardada a DTO
        CancionDTO cancionGuardadaDto = mapper.map(cancionGuardada, CancionDTO.class);

        // Retornar la respuesta con la canción creada
        return new ResponseEntity<>(cancionGuardadaDto, HttpStatus.CREATED);
    }



//    @PostMapping
//    public ResponseEntity<CancionDTO> registrarCancion(@Validated @RequestBody CancionDTO cancionNuevaDto) throws Exception {
//        // Mapear DTO a entidad
//        Cancion cancionNueva = mapper.map(cancionNuevaDto, Cancion.class);
//
//        // Verificar si ya existe una canción con el mismo nombre
//        Cancion cancionExistente = cancionServicio.findByCancionesPorNombre(cancionNueva.getNombreCancion());
//
//        if (cancionExistente != null) {
//            // Si existe, devolver NO_CONTENT o CONFLICT
//            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
//        } else {
//            // Guardar la nueva canción en la base de datos
//            Cancion cancionGuardada = cancionServicio.registrar(cancionNueva);
//
//            // Mapear la canción guardada a DTO
//            CancionDTO cancionGuardadaDto = mapper.map(cancionGuardada, CancionDTO.class);
//
//            // Devolver la canción creada con CREATED
//            return new ResponseEntity<>(cancionGuardadaDto, HttpStatus.CREATED);
//        }
//    }

//    @PostMapping
//    public ResponseEntity<Cancion> registrarCancion(@RequestBody Cancion cancion) throws Exception {
//        try {
//            Cancion nuevaCancion = cancionServicio.registrar(cancion);
//            return new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
//        } catch (IllegalArgumentException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/{id}") //MODIFICAR POR ID
    public ResponseEntity<CancionDTO> modificarCancionPorId(@PathVariable("id") Integer idCancion, @Validated @RequestBody CancionDTO idCancionModificada) throws Exception {
        // Verificar si la canción existe por ID
        Cancion cancionExistente = cancionServicio.listarPorId(idCancion);
        if (cancionExistente == null) {
            // Si no existe, retornar NOT FOUND
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Mapear los nuevos datos a la canción existente
        cancionExistente.setNombreCancion(idCancionModificada.getNombreCancion());
        cancionExistente.setFechaCreacion(idCancionModificada.getFechaCreacion());
        cancionExistente.setDuracion(idCancionModificada.getDuracion());
        cancionExistente.setBusquedas(idCancionModificada.getBusquedas());
        cancionExistente.setDescargas(idCancionModificada.getDescargas());
        cancionExistente.setInterpretes(idCancionModificada.getInterpretes());
        cancionExistente.setGenero(idCancionModificada.getGenero());

        // Guardar los cambios
        Cancion cancionActualizada = cancionServicio.modificar(cancionExistente);
        // Convertir a DTO para la respuesta
        CancionDTO cancionActualizadaDto = mapper.map(cancionActualizada, CancionDTO.class);

        return new ResponseEntity<>(cancionActualizadaDto, HttpStatus.OK);

    }

//    @PutMapping("/{id}") // MODIFICAR POR ID
//    public ResponseEntity<CancionDTO> modificarCancionPorId(@PathVariable("id") Integer idCancion, @Validated @RequestBody CancionDTO idCancionModificada) {
//        try {
//            // Verificar si la canción existe por ID
//            Cancion cancionExistente = cancionServicio.listarPorId(idCancion);
//
//            if (cancionExistente == null) {
//                // Si no existe, retornar NOT FOUND
//                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            }
//
//            // Validaciones adicionales de `idCancionModificada` si es necesario
//            if (idCancionModificada.getNombreCancion() == null || idCancionModificada.getNombreCancion().isEmpty()) {
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            }
//
//            // Mapear los nuevos datos a la canción existente
//            cancionExistente.setNombreCancion(idCancionModificada.getNombreCancion());
//            cancionExistente.setFechaCreacion(idCancionModificada.getFechaCreacion());
//            cancionExistente.setDuracion(idCancionModificada.getDuracion());
//            cancionExistente.setBusquedas(idCancionModificada.getBusquedas());
//            cancionExistente.setDescargas(idCancionModificada.getDescargas());
//
//            // Validación opcional para Interpretes y Genero
//            if (idCancionModificada.getInterpretes() != null) {
//                cancionExistente.setInterpretes(idCancionModificada.getInterpretes());
//            }
//
//            if (idCancionModificada.getGenero() != null) {
//                cancionExistente.setGenero(idCancionModificada.getGenero());
//            }
//
//            // Guardar los cambios
//            Cancion cancionActualizada = cancionServicio.modificar(cancionExistente);
//
//            // Convertir a DTO para la respuesta
//            CancionDTO cancionActualizadaDto = mapper.map(cancionActualizada, CancionDTO.class);
//            return new ResponseEntity<>(cancionActualizadaDto, HttpStatus.OK);
//
//        } catch (NoSuchElementException ex) {
//            // Manejo específico si listarPorId lanza esta excepción
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        } catch (Exception ex) {
//            // Manejo de otras excepciones no previstas
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    @DeleteMapping("nombre") //BORRAR POR NOMBRE
    public ResponseEntity<CancionDTO> eliminarCancionPorNombre(@PathVariable("nombre") String nombreCancion) throws Exception {
        Cancion cancionEliminada = cancionServicio.findByCancionesPorNombre(nombreCancion);
        if (cancionEliminada == null) {
            return new ResponseEntity<>(mapper.map(null, CancionDTO.class), HttpStatus.NO_CONTENT);
        } else {
            cancionServicio.borrarCancionPorNombre(cancionEliminada.getNombreCancion());
            cancionServicio.borrarCancionPorNombre(cancionEliminada.getNombreCancion());
            return new ResponseEntity<>(mapper.map(cancionEliminada, CancionDTO.class), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}") //BORRAR POR ID
    public ResponseEntity<CancionDTO> eliminarCancionPorId(@PathVariable("id") Integer idCancion) throws Exception {
        cancionServicio.eliminar(idCancion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
