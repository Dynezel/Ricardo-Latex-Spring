package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import universidad.ricardo_spring.entidades.Usuario;
import universidad.ricardo_spring.servicios.UsuarioService;

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
    public UserDetails getUser(@PathVariable String username) {
        return usuarioService.loadUserByUsername(username);
    }


}
