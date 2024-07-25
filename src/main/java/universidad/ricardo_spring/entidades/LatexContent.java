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
    @Lob // Asegúrate de que esta anotación esté presente si es un tipo de datos grande
    private byte[] pdf;
    private String categoria;
}
