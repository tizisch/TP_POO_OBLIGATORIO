package vehiculos;

import java.time.LocalDate;

public class BicicletaComun extends Vehiculo {
    private int tamanioRodado;

    public BicicletaComun(String codigo, LocalDate fechaAlta, int rodado, Estado estado) {
        super(codigo, fechaAlta);
        this.tamanioRodado = rodado;
        this.cambiarEstado(estado);
    }

    @Override
    public double calcularCosto(int minutos) {
    return 2;
    }
}
