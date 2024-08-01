package universidad.ricardo_spring.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

    // getters y setters
}