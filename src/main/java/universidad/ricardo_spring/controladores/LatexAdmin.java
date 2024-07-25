package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import universidad.ricardo_spring.entidades.LatexContent;
import universidad.ricardo_spring.servicios.LatexContentService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:5173")
@PreAuthorize("ROLE_ADMINISTRADOR")
@RequestMapping("/api/admin")
public class LatexAdmin {

    @Value("${pdf.upload.dir}")
    private String uploadDir;

    @Autowired
    LatexContentService latexContentService;

    @PostMapping("/create")
    public ResponseEntity<String> createContent(@RequestParam("title") String title,
                                                @RequestParam("content") String content,
                                                @RequestParam("categoria") String categoria,
                                                @RequestParam(value = "file", required = false) MultipartFile file) {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = null;
        if (file != null) {
            try {
                fileName = file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.write(filePath, file.getBytes());
                LatexContent latexContent = new LatexContent();
                latexContent.setTitle(title);
                latexContent.setContent(content);
                latexContent.setCategoria(categoria);
                latexContent.setPdfPath(filePath.toString());

                latexContentService.createLatexContent(latexContent);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error al guardar el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            LatexContent latexContent = new LatexContent();
            latexContent.setTitle(title);
            latexContent.setContent(content);
            latexContent.setCategoria(categoria);

            latexContentService.createLatexContent(latexContent);
        }

        return new ResponseEntity<>("Contenido creado exitosamente", HttpStatus.OK);
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
