package coreNetworking.tccBackProgramacionSistemas.Excepciones;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@Controller
@ControllerAdvice
public class InterceptadorErrores extends ResponseEntityExceptionHandler{

    public final ResponseEntity<ExcepcionRespuesta> manejadorTodasLasExcepciones(Exception ex, WebRequest request){
        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//

    @ExceptionHandler(ExcepcionNoEncontradoModelo.class)
    public ResponseEntity<ExcepcionRespuesta> manejadorExcepcionNoEncontradoModelo(ExcepcionNoEncontradoModelo ex,
                                                                                   WebRequest request) {
        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.NOT_FOUND);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String mensaje = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> {return e.getDefaultMessage()
                        .concat(",");})
                .collect(Collectors.joining());

        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(), mensaje, request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
    }

}
