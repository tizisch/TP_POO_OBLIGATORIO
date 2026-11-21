package menu;

import controladores.ControladorEstacion;
import excepciones.EstacionLlenaException;
import excepciones.EstacionNoEncontradaException;
import excepciones.VehiculoNoDisponibleException;
import vehiculos.Estacion;

import javax.swing.*;
import java.util.List;

public class MenuOperador extends Menu {
    private ControladorEstacion controladorEstacion;

    public MenuOperador(ControladorEstacion controladorEstacion) {
        this.controladorEstacion = controladorEstacion;
    }

    public void redistribuirVehiculo() {
        // 1. Le pedimos al controlador la lista de estaciones que necesitan redistribucion (menos del 20% o mas del 90% llenas)
        List<Estacion> estaciones = controladorEstacion.obtenerEstacionesParaRedistribuir();

        // 2. Armamos el texto que se va a mostrar en la ventana con los datos de cada estacion
        String mensaje = "Estaciones que necesitan redistribución:\n";
        for (Estacion estacion : estaciones) {
            String codigo = estacion.getCodigo();
            String direccion = estacion.getDireccion();
            int cantidadActual = estacion.getVehiculosEstacionados().size();
            int capacidadMaxima = estacion.getCapacidadMaxima();

            mensaje += codigo + " - " + direccion + " (" + cantidadActual + " de " + capacidadMaxima + " vehículos)\n";
        }

        // 3. Mostramos las estaciones en pantalla y le pedimos al operador los datos para mover el vehiculo
        String codigoOrigen = JOptionPane.showInputDialog(mensaje + "\nCódigo de estación de origen:");
        if (codigoOrigen == null) return;
        String codigoVehiculo = JOptionPane.showInputDialog("Código del vehículo a trasladar:");
        if (codigoVehiculo == null) return;
        String codigoDestino = JOptionPane.showInputDialog("Código de estación de destino:");
        if (codigoDestino == null) return;

        // 4. Llamamos al controlador para sacar el vehiculo de la estacion de origen y pasarlo a la de destino
        try {
            controladorEstacion.redistribuirVehiculo(codigoOrigen, codigoDestino, codigoVehiculo);
            JOptionPane.showMessageDialog(null, "Vehículo " + codigoVehiculo + " redistribuido de " + codigoOrigen + " a " + codigoDestino + ".");
        } catch (EstacionNoEncontradaException | VehiculoNoDisponibleException | EstacionLlenaException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
