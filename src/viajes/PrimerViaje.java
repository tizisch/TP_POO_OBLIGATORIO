package viajes;

import java.time.Duration;

public class PrimerViaje implements  Promocion {
    private String nombre = "Primer viaje";
    private int minutosGratis = 15;

    @Override
    public double calcularDescuento(Viaje viaje, double costo){
        if(viaje.getUsuario().getViajes().size() > 1){
            return 0.0;
        }else {
            long duracion = Duration.between(viaje.getFechaHoraInicio(), viaje.getFechaHoraFin()).toMinutes();
            if (duracion <= minutosGratis){ return costo;
            }else
                return viaje.getVehiculo().calcularCosto(minutosGratis);
        }
    }

    public String getNombre(){return nombre;}
}
