package repositorio;

import usuarios.Usuario;
import vehiculos.Estacion;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Repositorio {
    private List<Usuario> usuarios;
    private List<Estacion> estaciones;

    public Repositorio() {
        this.usuarios = new ArrayList<>();
        this.estaciones = new ArrayList<>();
    }
    private void guardarUsuario(Usuario usuario) {
        try (FileWriter fw = new FileWriter("usuarios.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(usuario.contenidoArchivo());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null && !this.usuarios.contains(usuario)) {
            this.usuarios.add(usuario);
            guardarUsuario(usuario);
        }
    }

    public void agregarEstacion(Estacion estacion) {
        if (estacion != null && !this.estaciones.contains(estacion)) {
            this.estaciones.add(estacion);
        }
    }

    public Usuario buscarUsuario(String documento) {
        if (documento == null) {
            return null;
        }
        for (Usuario usuario : this.usuarios) {
            if (documento.equalsIgnoreCase(usuario.getDocumento())) {
                return usuario;
            }
        }
        return null;
    }

    public Estacion buscarEstacion(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Estacion estacion : this.estaciones) {
            if (codigo.equalsIgnoreCase(estacion.getCodigo())) {
                return estacion;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }
}
