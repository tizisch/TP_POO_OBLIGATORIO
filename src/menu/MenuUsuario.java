package menu;

import controladores.ControladorCuenta;
import controladores.ControladorViajes;
import excepciones.*;
import usuarios.Usuario;

import java.util.Scanner;

public class MenuUsuario extends Menu{
    private ControladorCuenta controladorCuenta;
    private ControladorViajes controladorViajes;

    public MenuUsuario(Scanner sc,ControladorCuenta controladorCuenta, ControladorViajes controladorViajes) {
        super(sc);
        this.controladorCuenta = controladorCuenta;
        this.controladorViajes = controladorViajes;

    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n-----Menú Usuario -----");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Agregar saldo.");
        System.out.println("3. Retirar vehículo.");
        System.out.println("4. Devolver vehículo.");
        System.out.println("5. Suscribirse al plan mensual");
        System.out.println("0. Volver");
    }

    @Override
    protected void opcionElegida(int opcion) {
        switch (opcion) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                agregarSaldo();
                break;
            case 3:
                retirarVehiculo();
                break;
            case 4:
                devolverVehiculo();
                break;
            case 5:
                suscribirPlanMensual();
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }

    private void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        try {
            Usuario usuario = controladorCuenta.registrarUsuario(nombre, documento, email);
            System.out.println("Usuario registrado: " + nombre + ", Email: " +email);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void agregarSaldo() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();
        double importe = leerDouble("Importe que desea agregar: ");
        System.out.print("Medio de pago: ");
        String medioPago = sc.nextLine().trim();

        try {
            double nuevoSaldo = controladorCuenta.agregarSaldo(documento, importe, medioPago);
            System.out.println("Saldo agregado correctamente. Nuevo saldo: $" + nuevoSaldo);
        } catch (ImporteInvalidoException | UsuarioNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void retirarVehiculo() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();
        System.out.print("Codigo de estacion: ");
        String codigoEstacion = sc.nextLine().trim();
        System.out.print("Codigo de vehiculo: ");
        String codigoVehiculo = sc.nextLine().trim();

        try {
            controladorViajes.retirarVehiculo(documento, codigoVehiculo, codigoEstacion);
            System.out.println("Vehiculo retirado. Buen viaje!");
        } catch (UsuarioNoEncontradoException | EstacionNoEncontradaException
                 | VehiculoNoDisponibleException | RetiroNoPermitidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void devolverVehiculo() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();
        System.out.print("Codigo de estacion de devolucion: ");
        String codigoEstacion = sc.nextLine().trim();

        try {
            double costo = controladorViajes.devolverVehiculo(documento, codigoEstacion);
            System.out.println("Vehiculo devuelto. Costo del viaje: $" + costo);
        } catch (UsuarioNoEncontradoException | EstacionNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void suscribirPlanMensual() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();

        try {
            controladorCuenta.suscribirPlanMensual(documento);
            System.out.println("Te suscribiste al plan mensual.");
        } catch (SaldoInsuficienteException | PlanMensualActivoException | ImporteInvalidoException | UsuarioNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
