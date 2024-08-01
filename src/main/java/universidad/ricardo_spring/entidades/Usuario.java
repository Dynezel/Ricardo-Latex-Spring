package universidad.ricardo_spring.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import universidad.ricardo_spring.enumeraciones.Rol;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Puedes devolver los roles aquí. Si tienes más roles, puedes ajustar la lógica.
        return Collections.singletonList(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia según tus necesidades
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia según tus necesidades
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia según tus necesidades
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambia según tus necesidades
    }
}