package usuarios;
import java.util.ArrayList;
import java.util.List;


public class Usuario {
        private String nombre;
        private String documento;
        private String email;
        private Cuenta cuenta;
        private boolean tienePlanMensual;
        private List<Viaje> viajes;

        // ===== Constructor =====
        public Usuario(String nombre, String documento, String email) {
            this.nombre = nombre;
            this.documento = documento;
            this.email = email;
            this.cuenta = new Cuenta();
            this.tienePlanMensual = false;
            this.viajes = new ArrayList<>();
        }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
