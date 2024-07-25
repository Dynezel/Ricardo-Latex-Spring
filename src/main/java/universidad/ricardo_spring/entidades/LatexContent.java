package universidad.ricardo_spring.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class LatexContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content; // Contenido LaTeX
    private String pdfPath; // Ruta del archivo PDF
    private String categoria;
}
