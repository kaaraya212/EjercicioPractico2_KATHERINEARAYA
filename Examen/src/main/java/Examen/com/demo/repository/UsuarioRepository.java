package Examen.com.demo.repository;

import Examen.com.demo.domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kathe
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByRolNombre(String rolNombre);

    List<Usuario> findByCreatedBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Usuario> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(String n, String e);

    List<Usuario> findAllByOrderByCreatedDesc();
}
