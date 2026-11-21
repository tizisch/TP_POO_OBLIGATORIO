package controladores;

import excepciones.EstacionLlenaException;
import excepciones.EstacionNoEncontradaException;
import excepciones.VehiculoNoDisponibleException;
import repositorio.Repositorio;
import vehiculos.Estacion;
import vehiculos.Vehiculo;

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
            throw new IllegalArgumentException("Debe ingresar un código");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar una dirección");
        }
        if (coordenadas == null || coordenadas.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar las coordenadas");
        }
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a 0");
        }
        if (repositorio.buscarEstacion(codigo) != null) {
            throw new IllegalStateException("Ya existe una estación con el código " + codigo);
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

    public List<Estacion> obtenerEstacionesParaRedistribuir() {
        return repositorio.estacionesParaRedistribuir();
    }

    public void redistribuirVehiculo(String codigoOrigen, String codigoDestino, String codigoVehiculo)
            throws EstacionNoEncontradaException, VehiculoNoDisponibleException, EstacionLlenaException {
        Estacion estacionOrigen = repositorio.buscarEstacion(codigoOrigen);
        if (estacionOrigen == null) {
            throw new EstacionNoEncontradaException("No existe la estación de origen " + codigoOrigen);
        }

        Estacion estacionDestino = repositorio.buscarEstacion(codigoDestino);
        if (estacionDestino == null) {
            throw new EstacionNoEncontradaException("No existe la estación de destino " + codigoDestino);
        }

        Vehiculo vehiculo = estacionOrigen.buscarVehiculo(codigoVehiculo);
        if (vehiculo == null) {
            throw new VehiculoNoDisponibleException("El vehículo " + codigoVehiculo + " no está en la estación " + codigoOrigen);
        }

        if (!estacionDestino.tieneLugarDisponible()) {
            throw new EstacionLlenaException("La estación de destino " + codigoDestino + " está llena.");
        }

        estacionOrigen.quitarVehiculo(vehiculo);
        estacionDestino.agregarVehiculo(vehiculo);
    }
}