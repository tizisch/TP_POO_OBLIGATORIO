package menu;

import java.util.Scanner;
import controladores.ControladorCuenta;
import controladores.ControladorEstacion;
import usuarios.Usuario;
import vehiculos.Estacion;

public class MenuAdmin extends Menu{
    private ControladorCuenta controladorCuenta;
    private ControladorEstacion controladorEstacion;

    public MenuAdmin(Scanner sc, ControladorCuenta controladorCuenta, ControladorEstacion controladorEstacion) {
        super(sc);
        this.controladorCuenta = controladorCuenta;
        this.controladorEstacion = controladorEstacion;
    }

    public void mostrarMenu() {
        System.out.println("\n-----Menú Administrador -----");
        System.out.println("1. Buscar usuario");
        System.out.println("2. Agregar estacion");
        System.out.println("0. Volver.");
    }

    protected void opcionElegida(int opcion) {
        switch (opcion) {
            case 1:
                buscarUsuario();
                break;
            case 2:
                agregarEstacion();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }

    public void buscarUsuario() {
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();

        Usuario usuario = controladorCuenta.buscarUsuario(documento);

        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario);
        } else {
            System.out.println("No se encontro un usuario con ese documento.");
        }
    }

    public void agregarEstacion() {
        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine().trim();
        System.out.print("Coordenadas: ");
        String coordenadas = sc.nextLine().trim();
        int capacidadMaxima = leerEntero("Capacidad maxima: ");

        try {
            Estacion estacion = controladorEstacion.registrarEstacion(
                    codigo, direccion, coordenadas, capacidadMaxima);
            System.out.println("Estacion registrada: " + estacion);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
