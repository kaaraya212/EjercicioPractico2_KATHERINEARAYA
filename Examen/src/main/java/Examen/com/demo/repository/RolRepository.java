package Examen.com.demo.repository;

import Examen.com.demo.domain.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kathe
 */
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);
}
