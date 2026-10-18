package vehiculos;

import mantenimiento.OrdenMantenimiento;
import mantenimiento.ReporteFalla;

import java.time.LocalDate;
import java.util.List;

public abstract class Vehiculo {
    private String codigo;
    private LocalDate fechaAlta;
    private double kmRecorridos;
    private Estado estado;
    private List<OrdenMantenimiento> ordenesMantenimiento;

    public Vehiculo(String codigo, LocalDate fechaAlta) {
        this.codigo = codigo;
        this.fechaAlta = fechaAlta;
    }

    public boolean estaDisponible() {
    }

    public abstract double calcularCosto(int minutos);

    public void cambiarEstado(Estado nuevoEstado) {
    }

    public void agregarReporte(ReporteFalla reporte) {
    }

    public void agregarOrden(OrdenMantenimiento orden) {
    }
}
