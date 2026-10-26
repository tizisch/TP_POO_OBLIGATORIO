package vehiculos;

import java.time.LocalDate;

public class BicicletaElectrica extends Vehiculo {
    private int nivelBateria;
    private double autonomia;

    public BicicletaElectrica(String codigo, LocalDate fechaAlta, double autonomia) {
        super(codigo, fechaAlta);
        this.autonomia = autonomia;
        this.nivelBateria = 100;
    }

    //@Override
    //public boolean estaDisponible() {}

  @Override
   public double calcularCosto(int minutos) {
        return 3;
  }
}
