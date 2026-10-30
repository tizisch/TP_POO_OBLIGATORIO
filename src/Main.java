import controladores.ControladorCuenta;
import controladores.ControladorEstacion;
import controladores.ControladorViajes;
import menu.MenuAdmin;
import menu.MenuUsuario;
import repositorio.Repositorio;

import java.util.Scanner;

public class Main {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            Repositorio repositorio = new Repositorio();
            ControladorCuenta controladorCuenta = new ControladorCuenta(repositorio);
            ControladorViajes controladorViajes = new ControladorViajes(repositorio);
            ControladorEstacion controladorEstacion = new ControladorEstacion(repositorio);

            MenuUsuario menuUsuario = new MenuUsuario(sc, controladorCuenta, controladorViajes);
            MenuAdmin menuAdmin = new MenuAdmin(sc, controladorCuenta, controladorEstacion);

            int opcion = -1;
            while (opcion != 0) {
                System.out.println("\n----- MENU PRINCIPAL -----\n1. Usuario\n2. Administrador\n0. Salir");
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1:
                        menuUsuario.iniciar();
                        break;
                    case 2:
                        menuAdmin.iniciar();
                        break;
                }
            }
            System.out.println("Hasta luego");
        }
}
