package Examen.com.demo.service;

import Examen.com.demo.domain.Usuario;
import Examen.com.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author kathe
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public List<Usuario> findByRolName(String rolName) {
        return usuarioRepo.findByRolName(rolName);
    }

    @Override
    public List<Usuario> findByCreatedBetween(LocalDateTime desde, LocalDateTime hasta) {
        return usuarioRepo.findByFechaCreacionBetween(desde, hasta);
    }

    @Override
    public List<Usuario> searchByNameEmail(String term) {
        return usuarioRepo.searchByNameEmail(term);
    }

    @Override
    public long countActivos() {
        return usuarioRepo.countActivos();
    }

    @Override
    public long countInactivos() {
        return usuarioRepo.countInactivos();
    }

    @Override
    public List<Usuario> findAllOrderByCreatedDesc() {
        return usuarioRepo.findAllOrderByCreatedDesc();
    }
}
