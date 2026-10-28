package controladores;
import excepciones.ImporteInvalidoException;
import excepciones.UsuarioNoEncontradoException;
import repositorio.Repositorio;
import usuarios.Usuario;
public class ControladorCuenta {

    private Repositorio repositorio;
    public ControladorCuenta(Repositorio repositorio) {
        this.repositorio = repositorio;
    }


    public double agregarSaldo(String documento, double importe, String medioPago) throws ImporteInvalidoException, UsuarioNoEncontradoException {
        Usuario usuario = repositorio.buscarUsuario(documento);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No existe un usuario con documento " + documento);
        }
        return usuario.acreditarSaldo(importe, medioPago);
    }
}