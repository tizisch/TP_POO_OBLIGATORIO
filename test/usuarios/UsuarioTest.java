package usuarios;

import excepciones.ImporteInvalidoException;

public class UsuarioTest {

    public static void main(String[] args) {
        testPuedeRetirarExitoso();
        testPuedeRetirarDenegado();
        testAcreditarSaldoExitoso();
        testAcreditarSaldoImporteInvalido();
    }

    private static void testPuedeRetirarExitoso() {
        try {
            Usuario user = new Usuario("Alejo", "94309636", "alejo@test.com");
            user.acreditarSaldo(1000.0, "Mercado Pago");

            if (user.puedeRetirar(800.0)) {
                System.out.println("puedeRetirar con saldo suficiente devuelve true.");
            } else {
                System.out.println("puedeRetirar con saldo suficiente debió devolver true.");
            }
        } catch (Exception e) {
            System.out.println("Excepción en retiro exitoso: " + e.getMessage());
        }
    }

    private static void testPuedeRetirarDenegado() {
        try {
            Usuario user = new Usuario("Alejo", "94309636", "alejo@test.com");
            user.acreditarSaldo(500.0, "Mercado Pago");

            if (!user.puedeRetirar(800.0)) {
                System.out.println("puedeRetirar con saldo insuficiente devuelve false.");
            } else {
                System.out.println("puedeRetirar con saldo insuficiente debió devolver false.");
            }
        } catch (Exception e) {
            System.out.println("Excepción en retiro denegado: " + e.getMessage());
        }
    }

    private static void testAcreditarSaldoExitoso() {
        try {
            Usuario user = new Usuario("Alejo", "94309636", "alejo@test.com");
            double nuevoSaldo = user.acreditarSaldo(1500.0, "Tarjeta");

            if (nuevoSaldo == 1500.0) {
                System.out.println("acreditarSaldo devuelve y acredita el monto correcto (1500.0).");
            } else {
                System.out.println("acreditarSaldo no devolvió 1500.0. Saldo: " + nuevoSaldo);
            }
        } catch (Exception e) {
            System.out.println("Excepción en acreditarSaldo: " + e.getMessage());
        }
    }

    private static void testAcreditarSaldoImporteInvalido() {
        Usuario user = new Usuario("Alejo", "94309636", "alejo@test.com");

        // Importe negativo
        try {
            user.acreditarSaldo(-200.0, "Efectivo");
            System.out.println("Acreditar saldo negativo debió lanzar excepción.");
        } catch (ImporteInvalidoException e) {
            System.out.println("Acreditar saldo negativo lanzó ImporteInvalidoException correctamente.");
        }

        // Importe cero
        try {
            user.acreditarSaldo(0.0, "Efectivo");
            System.out.println("Acreditar saldo 0 debió lanzar excepción.");
        } catch (ImporteInvalidoException e) {
            System.out.println("Acreditar saldo 0 lanzó ImporteInvalidoException correctamente.");
        }
    }
}
