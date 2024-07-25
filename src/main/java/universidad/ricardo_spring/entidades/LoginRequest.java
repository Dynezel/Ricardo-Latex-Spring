package universidad.ricardo_spring.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginRequest {
    private String Username;
    private String password;

    // getters y setters
}