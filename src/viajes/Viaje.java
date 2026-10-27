package viajes;

import usuarios.Usuario;
import vehiculos.Estacion;
import vehiculos.Vehiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Viaje {
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private double distanciaRecorrido;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Estacion estacionOrigen;
    private Estacion estacionDestino;
    private List<PuntoRecorrido> puntosRecorrido;

    public Viaje(Usuario usuario, Vehiculo vehiculo, Estacion estacionOrigen) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.estacionOrigen = estacionOrigen;
        this.fechaHoraInicio = LocalDateTime.now();
        this.fechaHoraFin = null;
        this.estacionDestino = null;
        this.distanciaRecorrido = 0.0;
        this.puntosRecorrido = new ArrayList<>();
    }

    public void finalizar(Estacion estacionDestino, LocalDateTime fechaHoraFin) {
        this.estacionDestino = estacionDestino;
        this.fechaHoraFin = fechaHoraFin;
    }

    public double calcularCosto(Promocion promo) {
        return 0;
    }
}
