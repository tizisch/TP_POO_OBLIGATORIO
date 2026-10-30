package menu;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner sc;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Elegi una opcion: ");
            opcionElegida(opcion);
        }
    }

    protected abstract void mostrarMenu();
    protected abstract void opcionElegida(int opcion);

    protected int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }

    protected double leerDouble(String mensaje) {
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