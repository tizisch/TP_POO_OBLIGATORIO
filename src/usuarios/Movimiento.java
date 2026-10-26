package usuarios;

import java.time.LocalDateTime;

public class Movimiento {
    private final LocalDateTime fecha;
    private final double importe;
    private final String tipo;

    public Movimiento(LocalDateTime fecha, double importe, String tipo) {
        this.fecha = fecha;
        this.importe = importe;
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getImporte() {
        return importe;
    }

    public String getTipo() {
        return tipo;
    }
}
