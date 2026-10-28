package usuarios;

import excepciones.ImporteInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    public void puedeRetirar_retiroExitoso() throws ImporteInvalidoException {
        Usuario user = new Usuario("alejo", "94309636", "emailprueba@gmail.com");

        user.acreditarSaldo(1000, "Mercado Pago");

        assertTrue(user.puedeRetirar(800));
    }

    @Test
    public void puedeRetirar_retiroDenegado() throws ImporteInvalidoException {
        Usuario user = new Usuario("Alejo", "94309636", "emailprueba@gmail.com");

        user.acreditarSaldo(500, "Mercado Pago");

        assertFalse(user.puedeRetirar(800));
    }

    @Test
    public void acreditarSaldo_exitoso() throws ImporteInvalidoException {
        Usuario user = new Usuario("Alejo", "94309636", "emailprueba@gmail.com");

        double nuevoSaldo = user.acreditarSaldo(1500.0, "Tarjeta");

        assertEquals(1500.0, nuevoSaldo);
    }

    @Test
    public void acreditarSaldo_importeInvalido_lanzaExcepcion() {
        Usuario user = new Usuario("Alejo", "94309636", "emailprueba@gmail.com");

        // importe menor a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            user.acreditarSaldo(-200.0, "Efectivo");
        });

        // importe igual a 0
        assertThrows(ImporteInvalidoException.class, () -> {
            user.acreditarSaldo(0, "Efectivo");
        });
    }
}
