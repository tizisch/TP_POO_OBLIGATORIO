package vehiculos;

import java.util.ArrayList;
import java.util.List;

public class Estacion {

    private String codigo;
    private String direccion;
    private String coordenadas;         //coordenadas??? chquear esto, no aparece como atributo en el diagrama fibal de clases
    private int capacidadMaxima;
    private List<Vehiculo> vehiculosEstacionados;

    public Estacion(String codigo, String direccion, String coordenadas, int capacidadMaxima, List<Vehiculo> vehiculosEstacionados) {
        this.codigo = codigo; //tambien se habla de un capacidad en el diagrama, pero no aparece como atributo
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.capacidadMaxima = capacidadMaxima;
        this.vehiculosEstacionados = vehiculosEstacionados;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}