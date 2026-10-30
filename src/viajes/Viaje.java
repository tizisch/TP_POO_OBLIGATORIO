package viajes;

import usuarios.Usuario;
import vehiculos.*;


import java.time.Duration;
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

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }
    public LocalDateTime getFechaHoraInicio(){ return fechaHoraInicio; }
    public Usuario getUsuario(){return usuario;}
    public Vehiculo getVehiculo(){return vehiculo;}

/* acá vamos a ver dos cosas que no usamos en la clase viaje y sus metodos, que son el atributo distancia y la
lista puntos de recorrido. Identificamos que estos dos elementos, aunque presentes, en el viaje no eran muy útiles y
resutaban en mayor trabajo para crear y depues probar la clase de viajes. Los métodos como
finalizarViaje usan el tiempo para reducir la cantidad de batería usa la cantidad de minutos del viaje, al
igual que el calcular costo de viajes.





  */
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

    public void finalizar(Estacion estacionDestino) {
        this.estacionDestino = estacionDestino;
        this.fechaHoraFin = LocalDateTime.now();
        long tiempoViaje = Duration.between(this.fechaHoraInicio, this.fechaHoraFin).toMinutes();
        if (tiempoViaje > 1440) {
            vehiculo.cambiarEstado(Estado.FUERA_DE_SERVICIO);
        } else if (this.vehiculo instanceof BicicletaElectrica bici) {
            int bateria = bici.getbateria();
            int bateriaRestante = (int) (bateria - (0.5 * tiempoViaje));
            if (bateriaRestante < 0) {
                bateriaRestante = 0;
            }
            bici.setbateria(bateriaRestante);
            if (bici.getbateria() < 15) {
                bici.cambiarEstado(Estado.BATERIA_BAJA);
            } else {
                bici.cambiarEstado(Estado.DISPONIBLE);

            }
        } else if (this.vehiculo instanceof MonopatinElectrico monopatin) {
            int bateria = monopatin.getbateria();
            int bateriaRestante = (int) (bateria - (0.5 * tiempoViaje));
            if (bateriaRestante < 0) {
                bateriaRestante = 0;
            }
            monopatin.setbateria(bateriaRestante);
            if (monopatin.getbateria() < 15) {
                monopatin.cambiarEstado(Estado.BATERIA_BAJA);
            } else {
                monopatin.cambiarEstado(Estado.DISPONIBLE);
            }


        } else {
            vehiculo.cambiarEstado(Estado.DISPONIBLE);

        }


        }

    public double calcularCosto(Promocion promo) {
        int minutos = (int) Duration.between(this.fechaHoraInicio, this.fechaHoraFin).toMinutes();
        double costo = this.vehiculo.calcularCosto(minutos);

        if (promo != null) {
            costo = costo - promo.calcularDescuento(this, costo);
        }

        return costo;
    }




    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }
}



