package vehiculos;

import java.time.LocalDate;

public class MonopatinElectrico extends Vehiculo {
    private int nivelBateria;
    private double autonomia;
    private double velocidadMaxima;

    public MonopatinElectrico(String codigo, LocalDate fechaAlta, double velocidadMax) {
        super(codigo, fechaAlta);
        this.velocidadMaxima = velocidadMax;
        this.nivelBateria = 100;
    }

    @Override
    public boolean estaDisponible() {
        return true;
    }

    @Override
    public double calcularCosto(int minutos) {
        return 3;
    }
}
