package excepciones;

public class VehiculoNoDisponibleException extends Exception {

    public VehiculoNoDisponibleException(String message) {
        super("Vehiculo no disponible.");
    }
}
