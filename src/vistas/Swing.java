package vistas;

import menu.MenuAdmin;
import menu.MenuUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {
    private JPanel Main;
    private JButton botonSalir;
    private JButton botonUsuario;
    private JButton BotonAdmin;
    private JPanel menuPrincipal;

    private JPanel panelUsuario;
    private JButton botonRegistrarUsuario;
    private JButton botonAgregarSaldo;
    private JButton botonRetirarVehiculo;
    private JButton botonDevolverVehiculo;
    private JButton botonPlanMensual;
    private JButton botonVolverUsuario;

    private JPanel panelAdmin;
    private JButton botonBuscarUsuario;
    private JButton botonAgregarEstacion;
    private JButton botonVolverAdmin;

    private MenuUsuario menuUsuario;
    private MenuAdmin menuAdmin;

    public Swing(MenuUsuario menuUsuario, MenuAdmin menuAdmin) {
        this.menuUsuario = menuUsuario;
        this.menuAdmin = menuAdmin;

        botonUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) Main.getLayout();
                cl.show(Main, "vistaUsuario");
            }
        });
        botonVolverUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) Main.getLayout();
                cl.show(Main, "Card1");
            }
        });
        botonRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.registrarUsuario()).start();
            }
        });
        botonAgregarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.agregarSaldo()).start();
            }
        });
        botonRetirarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.retirarVehiculo()).start();
            }
        });
        botonDevolverVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.devolverVehiculo()).start();
            }
        });
        botonPlanMensual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.suscribirPlanMensual()).start();
            }
        });

        BotonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) Main.getLayout();
                cl.show(Main, "vistaAdmin");
            }
        });
        botonVolverAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) Main.getLayout();
                cl.show(Main, "Card1");
            }
        });
        botonBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuAdmin.buscarUsuario()).start();
            }
        });
        botonAgregarEstacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuAdmin.agregarEstacion()).start();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hasta luego");
                System.exit(0);
            }
        });
    }

    public void iniciar() {
        JFrame frame = new JFrame("Schipani Express");
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
