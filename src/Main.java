import controladores.ControladorCuenta;
import controladores.ControladorEstacion;
import controladores.ControladorViajes;
import menu.MenuAdmin;
import menu.MenuUsuario;
import repositorio.Repositorio;
import vistas.Swing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Repositorio repositorio = new Repositorio();
        repositorio.cargarUsuarios();

        ControladorCuenta controladorCuenta = new ControladorCuenta(repositorio);
        ControladorViajes controladorViajes = new ControladorViajes(repositorio);
        ControladorEstacion controladorEstacion = new ControladorEstacion(repositorio);

        MenuUsuario menuUsuario = new MenuUsuario(sc, controladorCuenta, controladorViajes);
        MenuAdmin menuAdmin = new MenuAdmin(sc, controladorCuenta, controladorEstacion);

        new Swing(menuUsuario, menuAdmin).iniciar();
    }
}
