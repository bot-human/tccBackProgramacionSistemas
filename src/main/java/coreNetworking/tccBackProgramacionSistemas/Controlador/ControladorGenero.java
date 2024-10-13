package coreNetworking.tccBackProgramacionSistemas.Controlador;

import coreNetworking.tccBackProgramacionSistemas.DTO.GeneroDTO;
import coreNetworking.tccBackProgramacionSistemas.Excepciones.ExcepcionNoEncontradoModelo;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IGeneroServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generos")
public class ControladorGenero {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IGeneroServicio generoServicio;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> consultarTodosLosTipos()throws Exception{
        List<GeneroDTO> TodosLosTiposG= generoServicio.listar()
                .stream().map(x-> mapper.map(x, GeneroDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(TodosLosTiposG, HttpStatus.OK);
    }

    @GetMapping("{nombreGenero}")
    public ResponseEntity<GeneroDTO> consultaPorTipoGenero(@PathVariable("nombreGenero")String tipoGenero) throws Exception{
        Genero generoBBDD = generoServicio.findByTipoGenero(tipoGenero);
        if (generoBBDD==null){
            throw new ExcepcionNoEncontradoModelo(tipoGenero + " no ha sido encontrado");
        }
        return new ResponseEntity<>(mapper.map(generoServicio.findByTipoGenero(tipoGenero), GeneroDTO.class),HttpStatus.OK);
    }

    @GetMapping("/{id}") //OBTENER UNA Genero POR ID
    public ResponseEntity<GeneroDTO> consultarGeneroPorId(@PathVariable("id") Integer idGenero) throws Exception {
        Genero generoPorId = generoServicio.listarPorId(idGenero);
        if (generoPorId == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        GeneroDTO generoDto = mapper.map(generoPorId, GeneroDTO.class);
        return new ResponseEntity<>(generoDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarGenero(@Validated @RequestBody GeneroDTO generoNuevoDto) throws Exception{
        // Mapear DTO a entidad
        Genero generoNuevo = mapper.map(generoNuevoDto, Genero.class);

        // Validación de tipoGenero
        if (generoNuevo.getTipoGenero() == null || generoNuevo.getTipoGenero().isEmpty()) {
            return new ResponseEntity<>("El campo 'tipoGenero' no puede ser nulo o vacío.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si ya existe un género con el mismo tipoGenero
        Genero generoExistente = generoServicio.findByTipoGenero(generoNuevo.getTipoGenero());
        if (generoExistente != null) {
            // Si existe, devolver CONFLICT
            return new ResponseEntity<>("Ya existe un género con este nombre.", HttpStatus.CONFLICT);
        }

        // Guardar el nuevo género en la base de datos
        Genero generoGuardado = generoServicio.registrar(generoNuevo);

        // Mapear la entidad guardada a DTO
        GeneroDTO generoGuardadoDto = mapper.map(generoGuardado, GeneroDTO.class);

        // Retornar la respuesta con el género creado
        return new ResponseEntity<>(generoGuardadoDto, HttpStatus.CREATED);
    }



//    @PostMapping
//    public ResponseEntity<GeneroDTO> registrarGenero(@Validated @RequestBody GeneroDTO generoNuevoDto) throws Exception{
//        Genero generoNuevo = mapper.map(generoNuevoDto,Genero.class);
//
//        Genero generoExistente = generoServicio.findByTipoGenero(generoNuevo.getTipoGenero());
//
//        if (generoExistente != null){
//            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
//        }
//        else {
//            Genero generoGuardado= generoServicio.registrar(generoNuevo);
//
//            GeneroDTO generoGuardadoDto =mapper.map(generoGuardado, GeneroDTO.class);
//
//            return new ResponseEntity<>(generoGuardadoDto,HttpStatus.CREATED);
//        }
//    }
//
//    @PostMapping("/generos")
//    public ResponseEntity<?> registrarPorTipoGenero(@RequestBody Genero genero) throws Exception {
//        try {
//            Genero nuevoGenero = generoServicio.registrar(genero);
//            return new ResponseEntity<>(nuevoGenero, HttpStatus.CREATED);
//        } catch (IllegalArgumentException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
//        }
//    }


    @PutMapping
    public ResponseEntity<GeneroDTO> modificarPorTipoGenero(@Validated @RequestBody GeneroDTO generoModificadoDto) throws Exception{
        Genero generoModificado =mapper.map(generoModificadoDto,Genero.class);
        Genero generoExistente = generoServicio.findByTipoGenero(generoModificado.getTipoGenero());
        if (generoExistente ==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); //cancion tiene implementacion diferente
        }
        else {
            return new ResponseEntity<>(mapper.map(generoServicio.modificar(generoExistente), GeneroDTO.class),HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> modificarGeneroPorId(@PathVariable Integer idGenero, @Validated @RequestBody GeneroDTO idGeneroModificado) throws Exception {
        Genero generoExistente = generoServicio.listarPorId(idGenero);
        if (generoExistente == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        generoExistente.setTipoGenero(idGeneroModificado.getTipoGenero());

        Genero generoActualizado = generoServicio.modificar(generoExistente);

        GeneroDTO generoActualizadoDto = mapper.map(generoActualizado, GeneroDTO.class);

        return new ResponseEntity<>(generoActualizadoDto, HttpStatus.OK);

    }


    @DeleteMapping("{nombre}")
    public ResponseEntity<GeneroDTO> eliminarPorTipoGenero(@PathVariable("nombre") String tipoGenero) throws Exception{
        Genero generoEliminado = generoServicio.findByTipoGenero(tipoGenero);
        if (generoEliminado ==null){
            return new ResponseEntity<>(mapper.map(null, GeneroDTO.class),HttpStatus.NO_CONTENT);
        }
        else {
            generoServicio.borrarGeneroPorTipo(generoEliminado.getTipoGenero());
            return new ResponseEntity<>(mapper.map(generoEliminado, GeneroDTO.class),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}") //BORRAR POR ID
    public ResponseEntity<GeneroDTO> eliminarGeneroPorId(@PathVariable("id") Integer idGenero) throws Exception {
        generoServicio.eliminar(idGenero);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
