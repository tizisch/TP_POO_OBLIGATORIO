package menu;

import java.util.Scanner;

public class MenuPrincipal extends Menu {
    private MenuUsuario menuUsuario;
    private MenuAdmin menuAdmin;

    public MenuPrincipal(Scanner sc, MenuUsuario menuUsuario, MenuAdmin menuAdmin) {
        super(sc);
        this.menuUsuario = menuUsuario;
        this.menuAdmin = menuAdmin;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n----- Menú principal -----");
        System.out.println("1. Usuario.");
        System.out.println("2. Administrador.");
        System.out.println("0. Salir.");
    }

    @Override
    public void opcionElegida(int opcion) {
        switch (opcion) {
            case 1:
                menuUsuario.iniciar();
                break;
            case 2:
                menuAdmin.iniciar();
                break;
            case 0:
                System.out.println("Hasta luego");
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }
}