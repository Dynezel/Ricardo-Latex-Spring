package universidad.ricardo_spring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universidad.ricardo_spring.entidades.LatexContent;
import universidad.ricardo_spring.repositorios.LatexContentRepositorio;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Service
public class LatexContentService {

    @Autowired
    private LatexContentRepositorio latexContentRepositorio;

    @Value("${pdf.upload.dir}")
    private String uploadDir;


    public List<LatexContent> getAllLatexContents() {
        return latexContentRepositorio.findAll();
    }

    public List<LatexContent> getLatexContentByCategoria(String categoria) {
        return latexContentRepositorio.findByCategoria(categoria);
    }


    public Optional<LatexContent> getLatexContentById(Long id) {
        return latexContentRepositorio.findById(id);
    }

    public LatexContent createLatexContent(LatexContent latexContent) {
        return latexContentRepositorio.save(latexContent);
    }

    public List<LatexContent> buscarPorTituloYCategoria(String title, String categoria) {
        if (title != null && !categoria.isEmpty()) {
            return latexContentRepositorio.findByTitleContainingIgnoreCaseAndCategoria(title, categoria);
        } else if (title != null) {
            return latexContentRepositorio.findByTitleContainingIgnoreCase(title);
        } else if (!categoria.isEmpty()) {
            return latexContentRepositorio.findByCategoria(categoria);
        } else {
            return latexContentRepositorio.findAll();
        }
    }

    public LatexContent updateLatexContent(Long id, LatexContent latexContent) {
        Optional<LatexContent> existingContent = latexContentRepositorio.findById(id);
        if (existingContent.isPresent()) {
            LatexContent updatedContent = existingContent.get();
            updatedContent.setTitle(latexContent.getTitle());
            updatedContent.setContent(latexContent.getContent());
            return latexContentRepositorio.save(updatedContent);
        } else {
            return null;
        }
    }

    public void deleteLatexContent(Long id) {
        latexContentRepositorio.deleteById(id);
    }
}
