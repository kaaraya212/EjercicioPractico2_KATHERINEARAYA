package Examen.com.demo.service;

import Examen.com.demo.domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kathe
 */
public interface UsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    List<Usuario> findByRolName(String rolName);

    List<Usuario> findByCreatedBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Usuario> searchByNameEmail(String term);

    long countActivos();

    long countInactivos();

    List<Usuario> findAllOrderByCreatedDesc();
}
