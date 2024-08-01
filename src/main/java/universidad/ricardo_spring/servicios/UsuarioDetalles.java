package universidad.ricardo_spring.servicios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import universidad.ricardo_spring.entidades.Usuario;

import java.util.Collection;

@Getter
public class UsuarioDetalles extends User {

    private Long id;

    public UsuarioDetalles(Usuario usuario) {
        super(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
        this.id = usuario.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}