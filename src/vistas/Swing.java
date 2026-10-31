package vistas;

import menu.MenuAdmin;
import menu.MenuUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {
    private JPanel panel1;
    private JButton botonSalir;
    private JButton botonUsario;
    private JButton BotonAdmin;

    private MenuUsuario menuUsuario;
    private MenuAdmin menuAdmin;

    public Swing(MenuUsuario menuUsuario, MenuAdmin menuAdmin) {
        this.menuUsuario = menuUsuario;
        this.menuAdmin = menuAdmin;

        botonUsario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuUsuario.iniciar()).start();
            }
        });
        BotonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> menuAdmin.iniciar()).start();
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
        JFrame frame = new JFrame("Schiapni Express");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
