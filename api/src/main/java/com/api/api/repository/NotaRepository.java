package com.api.api.repository;
import com.api.api.model.Nota;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota,Long>{
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort);
    List<Nota> findById(Long id, Sort sort);
    List<Nota> findByTituloContainingIgnoreCase(String titulo, Sort sort);
    List<Nota> findByContenidoContainingIgnoreCase(String contenido, Sort sort);
    List<Nota> findByTituloContainingIgnoreCaseOrContenidoContainingIgnoreCase(String titulo, String contenido, Sort sort);
}