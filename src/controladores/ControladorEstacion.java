package controladores;

import repositorio.Repositorio;
import vehiculos.Estacion;

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
}