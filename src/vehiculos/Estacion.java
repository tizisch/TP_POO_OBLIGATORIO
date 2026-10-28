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

    public boolean tieneLugarDisponible(){
        if (vehiculosEstacionados.size()>=capacidadMaxima){
            return false;

        }
        else{
            return true;
        }


    }

    public boolean requiereRedistribucion() {
        if (vehiculosEstacionados.size() < (capacidadMaxima / 100.0) * 20 || vehiculosEstacionados.size() > (capacidadMaxima / 100.0) * 90) {
            return true;

        } else {
            return false;
        }
    }
public void agregarVehiculo(Vehiculo vehiculo){
        if (vehiculosEstacionados.size()<capacidadMaxima){
            vehiculosEstacionados.add(vehiculo);

        }
        else{
            System.out.println("no se puede dejar el vehiculo en esta estacion");

        }
}

    public void quitarVehiculo(Vehiculo quitar) {
        if (quitar != null) {
            this.vehiculosEstacionados.remove(quitar);
        }
    }

    public Vehiculo buscarVehiculo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Vehiculo vehiculo : this.vehiculosEstacionados) {
            if (codigo.equalsIgnoreCase(vehiculo.getCodigo())) {
                return vehiculo;
            }
        }
        return null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public List<Vehiculo> getVehiculosEstacionados() {
        return vehiculosEstacionados;
    }
}

