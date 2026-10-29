package menu;

import repositorio.Repositorio;
import java.util.Scanner;
import controladores.ControladorCuenta;
import usuarios.Usuario;
import excepciones.ImporteInvalidoException;
import excepciones.UsuarioNoEncontradoException;

public class MenuConsola {
    private Repositorio repositorio;
    private Scanner sc;
    private ControladorCuenta controladorCuenta;

    public MenuConsola() {
        this.repositorio = new Repositorio();
        this.controladorCuenta = new ControladorCuenta(repositorio);
        this.sc = new Scanner(System.in);
    }


    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Elegi una opcion: ");
            opcionElegida(opcion);
        }
        System.out.println("Hasta luego");
    }

    private void mostrarMenu() {
        System.out.println("\n-----MENU PRINCIPAL -----");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Buscar usuario");
        System.out.println("3. Agregar saldo");
        System.out.println("4. Agregar estacion");
        System.out.println("0. Salir");
    }

    private void opcionElegida(int opcion) {
        switch (opcion) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                buscarUsuario();
                break;
            case 3:
                agregarSaldo();
                break;
            case 4:
                agregarEstacion();
            case 0:
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }


    private void buscarUsuario() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();

        Usuario usuario = repositorio.buscarUsuario(documento);

        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario);
        } else {
            System.out.println("No se encontro un usuario con ese documento.");
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
            System.out.println("Usuario registrado: " + usuario);
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
            System.out.println("Saldo agregado correctamente. Nuevo saldo: " + nuevoSaldo);
        } catch (ImporteInvalidoException | UsuarioNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }
    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un importe valido.");
            }
        }
    }
}


