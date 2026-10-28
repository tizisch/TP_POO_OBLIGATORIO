package controladores;

import excepciones.EstacionNoEncontradaException;
import excepciones.RetiroNoPermitidoException;
import excepciones.UsuarioNoEncontradoException;
import excepciones.VehiculoNoDisponibleException;
import repositorio.Repositorio;
import usuarios.Usuario;
import vehiculos.Estacion;
import vehiculos.Vehiculo;
import viajes.Viaje;

public class ControladorViajes {
    private Repositorio repositorio;

    public ControladorViajes(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Viaje retirarVehiculo(String documento, String codigoVehiculo, String codigoEstacion) throws UsuarioNoEncontradoException, VehiculoNoDisponibleException, RetiroNoPermitidoException, EstacionNoEncontradaException {
        Usuario usuario = repositorio.buscarUsuario(documento);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No existe un usuario con documento " + documento);
        }

        Estacion estacion = repositorio.buscarEstacion(codigoEstacion);
        if (estacion == null) {
            throw new EstacionNoEncontradaException("No existe la estacion " + codigoEstacion);
        }

        Vehiculo vehiculo = estacion.buscarVehiculo(codigoVehiculo);
        if (vehiculo == null) {
            throw new VehiculoNoDisponibleException("El vehiculo " + codigoVehiculo + " no esta en la estacion " + codigoEstacion);
        }
        Viaje viaje = usuario.crearViaje(vehiculo, estacion);
        estacion.quitarVehiculo(vehiculo);
        return viaje;
    }

    public double devolverVehiculo(String documento, String codigoEstacion) throws UsuarioNoEncontradoException, EstacionNoEncontradaException {
        Usuario usuario = repositorio.buscarUsuario(documento);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No existe un usuario con documento " + documento);
        }

        Estacion estacion = repositorio.buscarEstacion(codigoEstacion);
        if (estacion == null) {
            throw new EstacionNoEncontradaException("No existe la estacion " + codigoEstacion);
        }

        for (Viaje viaje : usuario.getViajes()) {
            if (viaje.getFechaHoraFin() == null) {
                viaje.finalizar(estacion);
                estacion.agregarVehiculo(viaje.getVehiculo());
                return viaje.calcularCosto(null);
            }
        }
        return 0;
    }
}
