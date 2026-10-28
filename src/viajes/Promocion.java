package viajes;

public interface Promocion {
    public double calcularDescuento(Viaje viaje, double costo);
    public String getNombre();
}
