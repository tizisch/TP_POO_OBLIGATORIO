package controladores;

import repositorio.Repositorio;
import vehiculos.Estacion;

import java.util.Comparator;
import java.util.List;

public class ControladorEstacion {

    private Repositorio repositorio;



    public ControladorEstacion(Repositorio repositorio) {
        this.repositorio = repositorio;
    }



    public Estacion registrarEstacion(String codigo, String direccion,
                                      String coordenadas, int capacidadMaxima) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar un codigo");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar una direccion");
        }
        if (coordenadas == null || coordenadas.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar las coordenadas");
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        }
        if (repositorio.buscarEstacion(codigo) != null) {
            throw new IllegalStateException("Ya existe una estacion con el codigo " + codigo);
        }

        Estacion estacion = new Estacion(codigo, direccion, coordenadas, capacidadMaxima);
        repositorio.agregarEstacion(estacion);
        return estacion;
    }


    public List<Estacion> obtenerEstacionesOrdenadasPorCapacidad() {
        List<Estacion> estaciones = repositorio.getEstaciones();
        estaciones.sort(new Comparator<Estacion>() {
            @Override
            public int compare(Estacion e1, Estacion e2) {
                return Integer.compare(e2.getCapacidadMaxima(), e1.getCapacidadMaxima());
            }
        });
        return estaciones;
    }
}