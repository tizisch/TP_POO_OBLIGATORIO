package usuarios;

import excepciones.ImporteInvalidoException;
import mantenimiento.ReporteFalla;
import vehiculos.Estacion;
import vehiculos.Estado;
import vehiculos.Vehiculo;
import viajes.Viaje;

import java.util.ArrayList;
import java.util.List;

import excepciones.RetiroNoPermitidoException;
import excepciones.VehiculoNoDisponibleException;

public class Usuario {
    private String nombre;
    private String documento;
    private String email;
    private Cuenta cuenta;
    private boolean tienePlanMensual;
    private List<Viaje> viajes;

    public List<Viaje> getViajes() {
        return viajes;
    }

    public String getDocumento() {
        return documento;
    }

    public Usuario(String nombre, String documento, String email){
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.tienePlanMensual = false;
        this.cuenta = new Cuenta();
        this.viajes = new ArrayList<>();
    }

    public boolean puedeRetirar(double saldoMinimo){
            return this.cuenta.getSaldo() >= saldoMinimo;
    }

    public double acreditarSaldo(double importe, String medioPago) throws ImporteInvalidoException {
        if(importe <= 0 ){
            throw new ImporteInvalidoException("El importe debe ser mayor a 0");
        }else
            return this.cuenta.recargar(importe,medioPago);
    }

    public boolean tieneViajeAbierto(){
        for(Viaje viaje : this.viajes){
            if (viaje.getFechaHoraFin() == null){
                return true;
            }
        }
        return false;
    }

    public Viaje crearViaje(Vehiculo vehiculo, Estacion estacionOrigen) throws RetiroNoPermitidoException, VehiculoNoDisponibleException{
        if(!tieneViajeAbierto() && puedeRetirar(vehiculo.calcularCosto(30))){
            if(vehiculo.estaDisponible()){
                Viaje viaje = new Viaje(this, vehiculo, estacionOrigen );
                viajes.add(viaje);
                vehiculo.cambiarEstado(Estado.EN_USO);
                //  estacionOrigen.quitarVehiculo(vehiculo);       // falta implementación de Estacion
                return viaje;
            } else
                throw new VehiculoNoDisponibleException("El vehículo seleccionado no se encuentra disponible.");
        } else
            throw new RetiroNoPermitidoException("Su cuenta no cuenta con el saldo mínimo requerido.");
    }

   // public ReporteFalla reportarFalla(Vehiculo vehiculo, String descripcion){}        // falta implementación de ReporteFalla
}
