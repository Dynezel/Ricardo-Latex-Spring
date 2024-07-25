package universidad.ricardo_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import universidad.ricardo_spring.entidades.LatexContent;
import universidad.ricardo_spring.repositorios.LatexContentRepositorio;
import universidad.ricardo_spring.servicios.LatexContentService;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/latex")
public class LatexContentController {


    @Value("${pdf.upload.dir}")
    private String uploadDir;

    private final Path pdfLocation = Paths.get("/src/main/resources/static/pdfs");

    @Autowired
    public LatexContentService latexContentService;

    @Autowired
    public LatexContentRepositorio latexContentRepositorio;

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<LatexContent> getAll() {
        return latexContentService.getAllLatexContents();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/categoria/{categoria}")
    public List<LatexContent> getByCategoria(@PathVariable String categoria) {
        return latexContentService.getLatexContentByCategoria(categoria);
    }

    @GetMapping("/buscar")
    public List<LatexContent> buscarPorTituloYCategoria(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String categoria) {
        return latexContentService.buscarPorTituloYCategoria(title, categoria);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Optional<LatexContent> getById(@PathVariable Long id) {
        return latexContentService.getLatexContentById(id);
    }


    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir, fileName);
            if (!Files.exists(filePath)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Resource resource = new ClassPathResource(filePath.toString());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
