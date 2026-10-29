package viajes;

import usuarios.Usuario;
import vehiculos.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViajeTest {

    public static void main(String[] args) {
        testFinalizarBiciComun();
        testFinalizarBiciElectrica();
        testFinalizarBateriaBaja();
        testFinalizarViajeLargo();
        testCostoSinPromo();
        testCostoPrimerViajeGratis();
        testCostoPrimerViajeExcedido();
    }

    private static void testFinalizarBiciComun() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaComun bicicletaComun = new BicicletaComun("BC01", LocalDate.now(), 26, Estado.EN_USO);

        Viaje viaje = new Viaje(usuario, bicicletaComun, estacionOrigen);
        viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(30));
        viaje.finalizar(estacionDestino);

        if (bicicletaComun.getEstado() == Estado.DISPONIBLE) {
            System.out.println("Finalizar viaje en bici comun cambia el estado correctamente (Estado: " + bicicletaComun.getEstado() + ")");
        } else {
            System.out.println("Finalizar viaje no dejo el estado esperado. Estado: " + bicicletaComun.getEstado());
        }
    }

    private static void testFinalizarBiciElectrica() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaElectrica bicicletaElectrica = new BicicletaElectrica("BE01", LocalDate.now(), 40.0);
        bicicletaElectrica.cambiarEstado(Estado.EN_USO);

        Viaje viaje = new Viaje(usuario, bicicletaElectrica, estacionOrigen);
        viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(20));
        viaje.finalizar(estacionDestino);

        if (bicicletaElectrica.getbateria() == 90 && bicicletaElectrica.getEstado() == Estado.DISPONIBLE) {
            System.out.println("Finalizar viaje en bici electrica descuenta bateria correctamente (Bateria: " + bicicletaElectrica.getbateria() + ")");
        } else {
            System.out.println("Finalizar viaje en bici electrica no dio el resultado esperado. Bateria: " + bicicletaElectrica.getbateria());
        }
    }

    private static void testFinalizarBateriaBaja() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        MonopatinElectrico monopatinElectrico = new MonopatinElectrico("ME01", LocalDate.now(), 25.0);
        monopatinElectrico.setbateria(20);
        monopatinElectrico.cambiarEstado(Estado.EN_USO);

        Viaje viaje = new Viaje(usuario, monopatinElectrico, estacionOrigen);
        viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(20));
        viaje.finalizar(estacionDestino);

        if (monopatinElectrico.getbateria() == 10 && monopatinElectrico.getEstado() == Estado.BATERIA_BAJA) {
            System.out.println("Finalizar viaje con menos de 15% pasa a bateria baja correctamente (Estado: " + monopatinElectrico.getEstado() + ")");
        } else {
            System.out.println("Finalizar viaje con bateria baja no cambio el estado. Estado: " + monopatinElectrico.getEstado());
        }
    }

    private static void testFinalizarViajeLargo() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaComun bicicletaComun = new BicicletaComun("BC02", LocalDate.now(), 26, Estado.EN_USO);

        Viaje viaje = new Viaje(usuario, bicicletaComun, estacionOrigen);
        viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(1500));
        viaje.finalizar(estacionDestino);

        if (bicicletaComun.getEstado() == Estado.FUERA_DE_SERVICIO) {
            System.out.println("Viaje mayor a 24 horas pasa el vehiculo a fuera de servicio correctamente.");
        } else {
            System.out.println("El vehiculo debio quedar fuera de servicio. Estado actual: " + bicicletaComun.getEstado());
        }
    }

    private static void testCostoSinPromo() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaComun bicicletaComun = new BicicletaComun("BC03", LocalDate.now(), 26, Estado.EN_USO);

        Viaje viaje = new Viaje(usuario, bicicletaComun, estacionOrigen);
        viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(30));
        viaje.finalizar(estacionDestino);

        double costoCalculado = viaje.calcularCosto(null);
        if (costoCalculado == 800.0) {
            System.out.println("calcularCosto sin promocion devuelve el monto correcto (800.0).");
        } else {
            System.out.println("calcularCosto no devolvio 800.0. Costo: " + costoCalculado);
        }
    }

    private static void testCostoPrimerViajeGratis() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaComun bicicletaComun = new BicicletaComun("BC04", LocalDate.now(), 26, Estado.DISPONIBLE);
        estacionOrigen.agregarVehiculo(bicicletaComun);

        try {
            usuario.acreditarSaldo(2000, "MercadoPago");
            Viaje viaje = usuario.crearViaje(bicicletaComun, estacionOrigen);
            viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(12));
            viaje.finalizar(estacionDestino);

            Promocion promocionPrimerViaje = new PrimerViaje();
            double costoCalculado = viaje.calcularCosto(promocionPrimerViaje);

            if (costoCalculado == 0.0) {
                System.out.println("Primer viaje menor a 15 minutos aplica descuento gratis correctamente (Costo: " + costoCalculado + ")");
            } else {
                System.out.println("Primer viaje gratis debio dar 0.0. Costo: " + costoCalculado);
            }
        } catch (Exception e) {
            System.out.println("Excepcion en primer viaje gratis: " + e.getMessage());
        }
    }

    private static void testCostoPrimerViajeExcedido() {
        Usuario usuario = new Usuario("Gonzalo", "44111222", "gonzalo@mail.com");
        Estacion estacionOrigen = new Estacion("E01", "Lima 775", "123", 10);
        Estacion estacionDestino = new Estacion("E02", "Corrientes 1200", "456", 10);
        BicicletaComun bicicletaComun = new BicicletaComun("BC05", LocalDate.now(), 26, Estado.DISPONIBLE);
        estacionOrigen.agregarVehiculo(bicicletaComun);

        try {
            usuario.acreditarSaldo(2000, "MercadoPago");
            Viaje viaje = usuario.crearViaje(bicicletaComun, estacionOrigen);
            viaje.setFechaHoraInicio(LocalDateTime.now().minusMinutes(25));
            viaje.finalizar(estacionDestino);

            Promocion promocionPrimerViaje = new PrimerViaje();
            double costoCalculado = viaje.calcularCosto(promocionPrimerViaje);

            if (costoCalculado == 200.0) {
                System.out.println("Primer viaje excedido cobra solo los minutos extra correctamente (Costo: " + costoCalculado + ")");
            } else {
                System.out.println("Primer viaje excedido debio dar 200.0. Costo: " + costoCalculado);
            }
        } catch (Exception e) {
            System.out.println("Excepcion en primer viaje excedido: " + e.getMessage());
        }
    }
}
