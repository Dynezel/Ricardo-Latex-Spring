package universidad.ricardo_spring.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
public class PortalControlador {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Servidor funcionando");
    }

    @GetMapping("/")
    public String saludo1() {
        return("Servidor funcionando");
    }

    @PreAuthorize("ROLE_USUARIO")
    @GetMapping("/hola")
    public String saludo() {
        return "Hola!";
    }

}
