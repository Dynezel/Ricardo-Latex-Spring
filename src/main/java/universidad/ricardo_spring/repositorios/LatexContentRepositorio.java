package universidad.ricardo_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import universidad.ricardo_spring.entidades.LatexContent;

import java.util.List;

public interface LatexContentRepositorio extends JpaRepository<LatexContent, Long> {
    @Query("SELECT l FROM LatexContent l WHERE UPPER(l.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<LatexContent> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("SELECT l FROM LatexContent l WHERE l.categoria = :categoria")
    List<LatexContent> findByCategoria(@Param("categoria") String categoria);

    @Query("SELECT l FROM LatexContent l WHERE UPPER(l.title) LIKE UPPER(CONCAT('%', :title, '%')) AND l.categoria = :categoria")
    List<LatexContent> findByTitleContainingIgnoreCaseAndCategoria(@Param("title") String title, @Param("categoria") String categoria);


}
