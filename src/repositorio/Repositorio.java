package repositorio;

import usuarios.Usuario;
import vehiculos.Estacion;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private List<Usuario> usuarios;
    private List<Estacion> estaciones;

    public Repositorio() {
        this.usuarios = new ArrayList<>();
        this.estaciones = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuario != null && !this.usuarios.contains(usuario)) {
            this.usuarios.add(usuario);
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
