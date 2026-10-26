package excepciones;

public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(String message) {
        super("El usuario ingresado no existe.");
    }
}
