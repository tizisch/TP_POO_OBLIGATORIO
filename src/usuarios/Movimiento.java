package usuarios;

import java.time.LocalDateTime;

public class Movimiento {
    private LocalDateTime fecha;
    private double importe;
    private String tipo;

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
