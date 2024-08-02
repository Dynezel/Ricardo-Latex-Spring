package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import universidad.ricardo_spring.entidades.LatexContent;
import universidad.ricardo_spring.servicios.LatexContentService;
import universidad.ricardo_spring.servicios.UsuarioService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "https://ricardo-latex-react.vercel.app") // Asegúrate de que este origen coincida con tu frontend
@RequestMapping("/api/admin")
public class LatexAdmin {

    @Value("${pdf.upload.dir}")
    private String uploadDir;

    @Value("${CREATION_KEY}")
    private String codigoCreacion;

    @Autowired
    LatexContentService latexContentService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<String> createContent(@RequestParam("title") String title,
                                                @RequestParam("content") String content,
                                                @RequestParam("categoria") String categoria,
                                                @RequestParam(value = "file", required = false) MultipartFile file,
                                                Authentication authentication) {
        try {
            if (authentication == null) {
                System.out.println("Authentication es null");
                return new ResponseEntity<>("Authentication es null", HttpStatus.UNAUTHORIZED);
            }

            String username = authentication.getName();
            System.out.println("Usuario autenticado: " + username);

            // Procesamiento y guardado del contenido
            LatexContent latexContent = new LatexContent();
            latexContent.setTitle(title);
            latexContent.setContent(content);
            latexContent.setCategoria(categoria);

            if (file != null) {
                byte[] fileBytes = file.getBytes();
                latexContent.setPdf(fileBytes);
            }

            latexContentService.createLatexContent(latexContent);

            return new ResponseEntity<>("Contenido creado exitosamente", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al guardar el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("ROLE_ADMINISTRADOR")
    @PutMapping("/{id}")
    public LatexContent update(@PathVariable Long id, @RequestBody LatexContent content) {
        return latexContentService.updateLatexContent(id, content);
    }

    @PreAuthorize("ROLE_ADMINISTRADOR")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        latexContentService.deleteLatexContent(id);
    }

}
