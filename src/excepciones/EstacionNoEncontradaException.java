package excepciones;

public class EstacionNoEncontradaException extends Exception {

    public EstacionNoEncontradaException() {
        super("Estacion no encontrada.");
    }

    public EstacionNoEncontradaException(String message) {
        super(message);
    }
}
