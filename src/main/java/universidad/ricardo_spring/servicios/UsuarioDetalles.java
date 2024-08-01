package universidad.ricardo_spring.servicios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UsuarioDetalles extends User {

    private Long id;

    public UsuarioDetalles(String username, String password, Collection<? extends GrantedAuthority> rol, Long id) {
        super(username, password, rol);
        this.id = id;
    }

    // Si necesitas un setter para id, puedes incluirlo aquí
    public void setId(Long id) {
        this.id = id;
    }
}