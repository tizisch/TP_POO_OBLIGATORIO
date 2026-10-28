package mantenimiento;

import java.time.LocalDate;

public class ReporteFalla {
    private String descripcion;
    private LocalDate fecha;

    public ReporteFalla(String descripcion, LocalDate fecha){
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
