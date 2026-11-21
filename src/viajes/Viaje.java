package viajes;

import usuarios.Usuario;
import vehiculos.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class Viaje implements Comparable<Viaje>{
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private double distanciaRecorrido;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Estacion estacionOrigen;
    private Estacion estacionDestino;
    private LocalDateTime fechaIntentoDevolucion;
    private static final int minutosDeCancelacion = 2;
    private static final int limiteMinutosGracia = 180;


    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }
    public LocalDateTime getFechaHoraInicio(){ return fechaHoraInicio; }
    public Usuario getUsuario(){return usuario;}
    public Vehiculo getVehiculo(){return vehiculo;}

    /*
     * Nota sobre atributos del diseño inicial que no se utilizan en la lógica actual:
     * - distanciaRecorrido (atributo) y puntosRecorrido (lista eliminada respecto al diseño original):
     *   Aunque formaban parte del modelo conceptual inicial del viaje, se decidió no utilizarlos
     *   porque tanto el consumo de batería (en finalizar) como el cálculo de la tarifa (en calcularCosto)
     *   se determinan en función de la duración en minutos del viaje (entre fechaHoraInicio y fechaHoraFin).
     * - estacionOrigen y estacionDestino:
     *   Se registran al iniciar y finalizar el viaje para mantener la trazabilidad del trayecto,
     *   aunque no intervienen directamente en el cálculo del costo.
     */
    public Viaje(Usuario usuario, Vehiculo vehiculo, Estacion estacionOrigen) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.estacionOrigen = estacionOrigen;
        this.fechaHoraInicio = LocalDateTime.now();
        this.fechaHoraFin = null;
        this.estacionDestino = null;
        this.fechaIntentoDevolucion = null;
        this.distanciaRecorrido = 0.0;
    }

    public void registrarIntentoFallido() {
        if (this.fechaIntentoDevolucion == null) {
            this.fechaIntentoDevolucion = LocalDateTime.now();
        }
    }
    @Override
    public int compareTo(Viaje otroViaje) {
        return otroViaje.fechaHoraInicio.compareTo(this.fechaHoraInicio);
    }

    public boolean estaFinalizado() {
        return fechaHoraFin != null;
    }

    public long getDuracionMinutos() {
        if (this.fechaIntentoDevolucion == null) {
            return Duration.between(fechaHoraInicio, fechaHoraFin).toMinutes();
        }

        long minutosDeViaje = Duration.between(fechaHoraInicio, fechaIntentoDevolucion).toMinutes();
        long minutosDeTraslado = Duration.between(fechaIntentoDevolucion, fechaHoraFin).toMinutes();

        if (minutosDeTraslado > limiteMinutosGracia) {
            return minutosDeViaje + (minutosDeTraslado - limiteMinutosGracia);
        }
        return minutosDeViaje;
    }

    public boolean fueCancelado() {
        return estaFinalizado() && getDuracionMinutos() <= minutosDeCancelacion;
    }


    public void finalizar(Estacion estacionDestino) {
        this.estacionDestino = estacionDestino;
        this.fechaHoraFin = LocalDateTime.now();
        long tiempoViaje = Duration.between(this.fechaHoraInicio, this.fechaHoraFin).toMinutes();
        if (tiempoViaje > 1440) {
            vehiculo.cambiarEstado(Estado.FUERA_DE_SERVICIO);
        } else {
            vehiculo.registrarUso((int) tiempoViaje);
        }
    }

    public double calcularCosto(Promocion promo) {
        int minutos = (int) getDuracionMinutos();
        double costo = this.vehiculo.calcularCosto(minutos);

        if (promo != null) {
            costo = costo - promo.calcularDescuento(this, costo);
        }

        return costo;
    }


    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estacion getEstacionOrigen() {
        return estacionOrigen;
    }

    public Estacion getEstacionDestino() {
        return estacionDestino;
    }

    @Override
    public String toString() {
        String destino;
        if (estacionDestino != null) {
            destino = estacionDestino.getCodigo();
        } else {
            destino = "-";
        }

        String fin;
        if (fechaHoraFin != null) {
            long minutos = java.time.Duration.between(fechaHoraInicio, fechaHoraFin).toMinutes();
            fin = fechaHoraFin.toString() + " (" + minutos + " min)";
        } else {
            fin = "En curso";
        }

        return "Vehiculo: " + vehiculo.getCodigo() +
                " | Origen: " + estacionOrigen.getCodigo() +
                " | Destino: " + destino +
                " | Inicio: " + fechaHoraInicio +
                " | Fin: " + fin;
    }
}





