package universidad.ricardo_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import universidad.ricardo_spring.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.email =  :email")
    public Usuario buscarPorEmail(@Param("email") String email);
}
