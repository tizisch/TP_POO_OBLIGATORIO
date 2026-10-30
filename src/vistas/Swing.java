package vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {
    private JTextField textField1;
    private JTextField textField2;

    public Swing() {
        if (textField1 != null) {
            textField1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String texto1 = textField1.getText();
                    System.out.println("Texto ingresado en caja 1: " + texto1);
                }
            });
        }

        if (textField2 != null) {
            textField2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String texto2 = textField2.getText();
                    System.out.println("Texto ingresado en caja 2: " + texto2);
                }
            });
        }
    }
}
