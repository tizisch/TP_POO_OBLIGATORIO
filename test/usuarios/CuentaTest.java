package usuarios;

import excepciones.ImporteInvalidoException;

public class CuentaTest {

    public static void main(String[] args) {
        testRecargarAumentaSaldo();
        testDebitarExitoso();
        testDebitarImporteInvalido();
        testDebitarSuperaSaldoQuedaNegativo();
    }

    private static void testRecargarAumentaSaldo() {
        try {
            Cuenta cuenta = new Cuenta();
            cuenta.recargar(1000.0, "Mercado Pago");

            if (cuenta.getSaldo() == 1000.0) {
                System.out.println(" Recargar saldo aumenta el saldo correctamente (Saldo: " + cuenta.getSaldo() + ")");
            } else {
                System.out.println(" Recargar saldo no dio el saldo esperado. Saldo: " + cuenta.getSaldo());
            }
        } catch (Exception e) {
            System.out.println("Excepción en recarga: " + e.getMessage());
        }
    }

    private static void testDebitarExitoso() {
        try {
            Cuenta cuenta = new Cuenta();
            cuenta.recargar(1000.0, "Mercado Pago");
            cuenta.debitar(500.0, "VIAJE");

            if (cuenta.getSaldo() == 500.0) {
                System.out.println("Débito exitoso descuenta el saldo correctamente (Saldo restante: " + cuenta.getSaldo() + ")");
            } else {
                System.out.println("Débito exitoso no dejó el saldo esperado: " + cuenta.getSaldo());
            }
        } catch (Exception e) {
            System.out.println("Excepción en débito exitoso: " + e.getMessage());
        }
    }

    private static void testDebitarImporteInvalido() {
        Cuenta cuenta = new Cuenta();

        try {
            cuenta.debitar(-500.0, "VIAJE");
            System.out.println("Débito negativo debió lanzar excepción.");
        } catch (ImporteInvalidoException e) {
            System.out.println("ébito negativo lanzó ImporteInvalidoException correctamente.");
        }

        try {
            cuenta.debitar(0.0, "VIAJE");
            System.out.println("Débito con 0 debió lanzar excepción.");
        } catch (ImporteInvalidoException e) {
            System.out.println("Débito con 0 lanzó ImporteInvalidoException correctamente.");
        }
    }

    private static void testDebitarSuperaSaldoQuedaNegativo() {
        try {
            Cuenta cuenta = new Cuenta();
            cuenta.recargar(400.0, "Mercado Pago");
            cuenta.debitar(500.0, "VIAJE");

            if (cuenta.getSaldo() == -100.0) {
                System.out.println("Débito mayor al saldo deja la cuenta en saldo negativo (-100.0).");
            } else {
                System.out.println("El saldo final no fue -100.0. Saldo actual: " + cuenta.getSaldo());
            }
        } catch (Exception e) {
            System.out.println("Excepción en débito con saldo negativo: " + e.getMessage());
        }
    }
}