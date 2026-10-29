package menu;

import repositorio.Repositorio;
import java.util.Scanner;
import controladores.ControladorCuenta;
import usuarios.Usuario;

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
        System.out.println("Hasta luego!");
    }

    private void mostrarMenu() {
        System.out.println("\n-----MENU PRINCIPAL -----");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Buscar usuario");
        System.out.println("3. Agregar saldo");
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
            case 0:
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }


    private void buscarUsuario() {
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        repositorio.buscarUsuario(documento);
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

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido.");
            }
        }
    }
}


