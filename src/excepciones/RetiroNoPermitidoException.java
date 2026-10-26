package excepciones;

public class RetiroNoPermitidoException extends Exception {

    public RetiroNoPermitidoException(String message) {
        super("No puede retirar el vehículo.");
    }
}
