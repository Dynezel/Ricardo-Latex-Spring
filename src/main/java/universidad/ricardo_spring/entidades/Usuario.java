package universidad.ricardo_spring.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;
import universidad.ricardo_spring.enumeraciones.Rol;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    // Getters y Setters
}
