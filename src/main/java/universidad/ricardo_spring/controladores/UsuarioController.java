package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import universidad.ricardo_spring.entidades.Usuario;
import universidad.ricardo_spring.servicios.UsuarioService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("https://ricardo-latex-react.vercel.app")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public Usuario register(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        return usuarioService.save(username, password);
    }

    @GetMapping("/{username}")
    public Usuario getUser(@PathVariable String username) {
        return usuarioService.findByUsername(username);
    }

    @GetMapping("/role")
    public ResponseEntity<Map<String, String>> getUserRole(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        if (authentication == null) {
            response.put("role", ""); // Devolver una cadena vacía en lugar de null
        } else {
            String role = authentication.getAuthorities().toString();
            response.put("role", role);
        }
        return ResponseEntity.ok(response);
    }

}
