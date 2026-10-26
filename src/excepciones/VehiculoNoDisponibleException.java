package excepciones;

public class VehiculoNoDisponibleException extends Exception {

    public VehiculoNoDisponibleException(String message) {
        super("Vehículo solicitado no disponible.");
    }
}
