package vehiculos;

import java.util.ArrayList;
import java.util.List;

public class Estacion {

    private String codigo;
    private String direccion;
    private String coordenadas;
    private int capacidadMaxima;
    private List<Vehiculo> vehiculosEstacionados;

    public Estacion(String codigo, String direccion, String coordenadas, int capacidadMaxima) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.capacidadMaxima = capacidadMaxima;
        this.vehiculosEstacionados = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}