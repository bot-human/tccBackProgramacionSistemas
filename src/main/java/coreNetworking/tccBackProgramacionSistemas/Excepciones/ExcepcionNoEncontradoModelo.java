package coreNetworking.tccBackProgramacionSistemas.Excepciones;

public class ExcepcionNoEncontradoModelo extends RuntimeException{

    private static final long serialVersionID=1L;

    public ExcepcionNoEncontradoModelo(String mensaje) {
        super(mensaje);
    }
}
