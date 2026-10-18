package vehiculos;

import java.time.LocalDate;

public class MonopatinElectrico extends Vehiculo {
    private int nivelBateria;
    private double autonomia;
    private double velocidadMaxima;

    public MonopatinElectrico(String codigo, LocalDate fechaAlta, double velMax) {
        super(codigo, fechaAlta);
        this.velocidadMaxima = velMax;
    }

    @Override
    public boolean estaDisponible() {
    }

    @Override
    public double calcularCosto(int minutos) {
    }
}
