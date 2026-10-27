package vehiculos;

import mantenimiento.OrdenMantenimiento;
import mantenimiento.ReporteFalla;

import java.time.LocalDate;
import java.util.ArrayList;
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
        this.kmRecorridos = 0.0;
        this.estado = Estado.DISPONIBLE;
        this.ordenesMantenimiento = new ArrayList<>();
    }

    public boolean estaDisponible() {
        return this.estado == Estado.DISPONIBLE;
    }

    public abstract double calcularCosto(int minutos);

    public String getCodigo() {
        return this.codigo;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void agregarReporte(ReporteFalla reporte) {
    }

    public void agregarOrden(OrdenMantenimiento orden) {
    }
}
