package coreNetworking.tccBackProgramacionSistemas.Controlador;

import coreNetworking.tccBackProgramacionSistemas.DTO.InterpreteDTO;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IInterpreteServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interpretes")
public class ControladorInterprete {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IInterpreteServicio interpreteServicio;


    @GetMapping //OBTENER TODOS
    public ResponseEntity<List<InterpreteDTO>> consultarTodosLosInterpretes()throws Exception{
        List<InterpreteDTO> TodasLosI = interpreteServicio.listar()
                .stream().map(x-> mapper.map(x, InterpreteDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(TodasLosI, HttpStatus.OK);
    }


    @GetMapping("nombreInterprete")
    public ResponseEntity<InterpreteDTO> consultarInterpretePorNombre (@PathVariable("nombreInterprete")String nombreInterprete) throws Exception {
        Interprete interpreteBBDD = interpreteServicio.findByInterpretePorNombre(nombreInterprete);
        if (interpreteBBDD == null) {
            return null;
        } else {
            return new ResponseEntity<>(mapper.map(interpreteServicio.findByInterpretePorNombre(nombreInterprete),
                    InterpreteDTO.class), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}") //OBTENER UNA Interprete POR ID
    public ResponseEntity<InterpreteDTO> consultarInterpretePorId(@PathVariable("id") Integer idInterprete) throws Exception {
        Interprete interpretePorId = interpreteServicio.listarPorId(idInterprete);
        if (interpretePorId == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        InterpreteDTO interpreteDto = mapper.map(interpretePorId, InterpreteDTO.class);
        return new ResponseEntity<>(interpreteDto, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<InterpreteDTO> registrarInterprete(@Validated @RequestBody InterpreteDTO interpreteNuevoDto){
//        Interprete interprete=mapper.map(interpreteNuevoDto, Interprete.class);
//        if (interpreteServicio.findByInterpretePorNombre(interprete.getNombre())!=null){
//            return new ResponseEntity<>(mapper.map(null, InterpreteDTO.class),HttpStatus.NO_CONTENT);
//        }
//        else {
//            interpreteServicio.save(interprete);//save o registrar
//            return new ResponseEntity<>(mapper.map(interprete,InterpreteDTO.class),HttpStatus.OK);
//        }
//    }

    @PostMapping
    public ResponseEntity<?> registrarInterprete(@Validated @RequestBody InterpreteDTO interpreteNuevoDto) {
        // Mapear DTO a entidad
        Interprete interprete = mapper.map(interpreteNuevoDto, Interprete.class);

        // Validación de nombre
        if (interprete.getNombre() == null || interprete.getNombre().isEmpty()) {
            return new ResponseEntity<>("El campo 'nombre_interprete' no puede ser nulo o vacío.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si ya existe un intérprete con el mismo nombre
        if (interpreteServicio.findByInterpretePorNombre(interprete.getNombre()) != null) {
            // Si existe, devolver CONFLICT
            return new ResponseEntity<>("Ya existe un intérprete con este nombre.", HttpStatus.CONFLICT);
        }

        // Guardar el nuevo intérprete
        Interprete interpreteGuardado = interpreteServicio.save(interprete);

        // Mapear la entidad guardada a DTO
        InterpreteDTO interpreteGuardadoDto = mapper.map(interpreteGuardado, InterpreteDTO.class);

        // Retornar la respuesta con el intérprete creado
        return new ResponseEntity<>(interpreteGuardadoDto, HttpStatus.CREATED);
    }



    @PutMapping
    public ResponseEntity<InterpreteDTO> modificarInterpretePorNombre(@Validated @RequestBody InterpreteDTO interpreteModificadoDto) throws Exception {
        Interprete interpreteModificado =mapper.map(interpreteModificadoDto, Interprete.class);
        Interprete interpreteExistente = interpreteServicio.findByInterpretePorNombre(interpreteModificado.getNombre());
        if (interpreteExistente ==null){
            return new ResponseEntity<>(mapper.map(null, InterpreteDTO.class),HttpStatus.NO_CONTENT);
        }
        else {
            interpreteServicio.save(interpreteModificado);
            return new ResponseEntity<>(mapper.map(interpreteServicio.modificar(interpreteExistente),InterpreteDTO.class),HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterpreteDTO> modificarInterpretePorId(@PathVariable Integer idInterprete, @Validated @RequestBody InterpreteDTO idInterpreteModificado) throws Exception {
        Interprete interpreteExistente = interpreteServicio.listarPorId(idInterprete);
        if (interpreteExistente == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        interpreteExistente.setNombre(idInterpreteModificado.getNombre());
        interpreteExistente.setFechaCreacion(idInterpreteModificado.getFechaCreacion());
        interpreteExistente.setPaisOrigen(idInterpreteModificado.getPaisOrigen());


        Interprete interpreteActualizado = interpreteServicio.modificar(interpreteExistente);

        InterpreteDTO interpreteActualizadoDto = mapper.map(interpreteActualizado, InterpreteDTO.class);

        return new ResponseEntity<>(interpreteActualizadoDto, HttpStatus.OK);

    }


    @DeleteMapping("{nombreInterprete}")
    public ResponseEntity<InterpreteDTO> eliminarInterpretePorNombre(@PathVariable("nombre") String nombreInterprete) throws Exception{
        Interprete interprete = interpreteServicio.findByInterpretePorNombre(nombreInterprete);
        if (interprete ==null){
            return new ResponseEntity<>(mapper.map(null, InterpreteDTO.class),HttpStatus.NO_CONTENT);
        }
        else {
            interpreteServicio.borrarInterpretePorNombre(interprete.getNombre());
            interpreteServicio.borrarInterpretePorNombre(interprete.getNombre());
            return new ResponseEntity<>(mapper.map(interprete, InterpreteDTO.class),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}") //BORRAR POR ID
    public ResponseEntity<InterpreteDTO> eliminarInterpretePorId(@PathVariable("id") Integer idInterprete) throws Exception {
        interpreteServicio.eliminar(idInterprete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
