package universidad.ricardo_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import universidad.ricardo_spring.entidades.Usuario;
import universidad.ricardo_spring.servicios.UsuarioDetalles;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

}
