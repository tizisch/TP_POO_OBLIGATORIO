package excepciones;

public class EstacionNoEncontradaException extends Exception {

    public EstacionNoEncontradaException() {
        super("Estación no encontrada.");
    }

    public EstacionNoEncontradaException(String message) {
        super(message);
    }
}
