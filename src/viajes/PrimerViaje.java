package viajes;

import java.time.Duration;

public class PrimerViaje implements  Promocion {
    private String nombre = "Primer viaje";
    private int minutosGratis = 15;

    @Override
    public double calcularDescuento(Viaje viaje, double costo){
        if (viaje.getUsuario().cantidadViajesPrevios(viaje) > 0){
            return 0.0;
        } else {
            long duracion = viaje.getDuracionMinutos();
            if (duracion <= minutosGratis){ return costo;
            }else
                return viaje.getVehiculo().calcularCosto(minutosGratis);
        }
    }

    public String getNombre(){return nombre;}
}
