package usuarios;

import excepciones.ImporteInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    @Test
    public void recargar_aumentaElSaldoCorrectamente() throws ImporteInvalidoException {

        Cuenta cuenta = new Cuenta();

        cuenta.recargar(1000.0, "Mercado Pago");

        assertEquals(1000.0, cuenta.getSaldo());
    }

    @Test
    public void recargar_lanzaExcepcionImporte() throws ImporteInvalidoException {
        Cuenta cuenta = new Cuenta();

        // importe menor a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            cuenta.recargar(-500, "Mercado Pago");
        });

        // importe igual a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            cuenta.recargar(0, "Mercado Pago");
        });
    }
    @Test
    public void debitar_exitoso() throws ImporteInvalidoException {
        Cuenta cuenta = new Cuenta();

        cuenta.recargar(1000,"Mercado Pago");
        cuenta.debitar(500,"VIAJE");

        assertEquals(500,cuenta.getSaldo());

    }
    @Test
    public void debitar_lanzaExcepcionImporte() {
        Cuenta cuenta = new Cuenta();

        // importe menor a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            cuenta.debitar(-500, "VIAJE");
        });

        // importe igual a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            cuenta.debitar(0, "VIAJE");
        });
    }
}