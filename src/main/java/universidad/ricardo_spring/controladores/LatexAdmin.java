package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import universidad.ricardo_spring.entidades.LatexContent;
import universidad.ricardo_spring.servicios.LatexContentService;
import universidad.ricardo_spring.servicios.UsuarioService;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://ricardo-latex-react.vercel.app") // Asegúrate de que este origen coincida con tu frontend
@RequestMapping("/api/admin")
public class LatexAdmin {

    @Value("${pdf.upload.dir}")
    private String uploadDir;

    @Value("${creation.key}")
    private String codigoCreacion;

    @Autowired
    LatexContentService latexContentService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create/3d9b4c8e-2764-4d1f-84df-12fa9a123d49-93b1f0d9c7a8a9cba5e9d8c3a7b2f4e6")
    public ResponseEntity<String> createContent(@RequestParam("title") String title,
                                                @RequestParam("content") String content,
                                                @RequestParam("categoria") String categoria,
                                                @RequestParam(value = "file", required = false) MultipartFile file,
                                                @RequestParam("creationKey") String creationKey) {
        try {
            // Verificar la clave de creación
            if (!codigoCreacion.equals(creationKey)) {
                return new ResponseEntity<>("Clave de creación incorrecta", HttpStatus.UNAUTHORIZED);
            }
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

    @PostMapping("/verify-code/6f9d3c2e-1a5b-4d6e-90bc-89f3c4e7b1a2-47e1c9d6f0a5b8d7e6c9a3b2c1f8e0d9")
    public ResponseEntity<Boolean> verifyCreationCode(@RequestBody Map<String, String> requestBody) {
        String receivedCode = requestBody.get("code").trim();

        if (codigoCreacion.equals(receivedCode)) {
            return ResponseEntity.ok(true);
        } else {
            // Detailed comparison for debugging
            if (codigoCreacion.length() == receivedCode.length()) {
                for (int i = 0; i < codigoCreacion.length(); i++) {
                    if (codigoCreacion.charAt(i) != receivedCode.charAt(i)) {
                        System.out.println("Mismatch at index " + i + ": expected '" + codigoCreacion.charAt(i) + "', received '" + receivedCode.charAt(i) + "'");
                    }
                }
            }
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/{id}")
    public LatexContent update(@PathVariable Long id, @RequestBody LatexContent content) {
        return latexContentService.updateLatexContent(id, content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        latexContentService.deleteLatexContent(id);
    }

}
