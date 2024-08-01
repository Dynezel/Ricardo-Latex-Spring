package universidad.ricardo_spring.servicios;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class UsuarioDetalles extends User {

    // Getters y setters adicionales
    private Long id;

    public UsuarioDetalles(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}