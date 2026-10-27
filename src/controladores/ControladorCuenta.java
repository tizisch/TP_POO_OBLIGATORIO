package controladores;
import repositorio.Repositorio;
import usuarios.Usuario;
import excepciones.ImporteInvalidoException;

public class ControladorCuenta {

    private Repositorio repositorio;

    public ControladorCuenta(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public double agregarSaldo(String documento, double importe, String medioPago) throws ImporteInvalidoException {
        Usuario usuario = repositorio.buscarUsuario(documento);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe un usuario con documento " + documento);
        }
        return usuario.acreditarSaldo(importe, medioPago);
    }

}

