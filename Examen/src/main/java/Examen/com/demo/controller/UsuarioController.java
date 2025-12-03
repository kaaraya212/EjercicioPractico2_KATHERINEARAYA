package Examen.com.demo.controller;

import Examen.com.demo.domain.Usuario;
import Examen.com.demo.service.UsuarioService;
import ch.qos.logback.core.model.Model;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kathe
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // -------------------------------------------------------
    // LISTAR TODOS LOS USUARIOS
    // -------------------------------------------------------
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuario/lista"; // vista Thymeleaf
    }

    // -------------------------------------------------------
    // FORMULARIO NUEVO
    // -------------------------------------------------------
    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/form";
    }

    // -------------------------------------------------------
    // GUARDAR (crear o editar)
    // -------------------------------------------------------
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/lista";
    }

    // -------------------------------------------------------
    // EDITAR
    // -------------------------------------------------------
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {

        Optional<Usuario> opt = usuarioService.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/usuario/lista";
        }

        model.addAttribute("usuario", opt.get());
        return "usuario/form";
    }

    // -------------------------------------------------------
    // ELIMINAR
    // -------------------------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/lista";
    }

    // -------------------------------------------------------
    // FILTRAR POR ROL
    // -------------------------------------------------------
    @GetMapping("/filtrar/rol")
    public String filtrarPorRol(@RequestParam String rol, Model model) {
        model.addAttribute("usuarios", usuarioService.findByRolName(rol));
        return "usuario/lista";
    }

    // -------------------------------------------------------
    // BUSCAR POR NOMBRE O CORREO
    // -------------------------------------------------------
    @GetMapping("/buscar")
    public String buscar(@RequestParam("term") String term, Model model) {
        model.addAttribute("usuarios", usuarioService.searchByNameEmail(term));
        return "usuario/lista";
    }

    // -------------------------------------------------------
    // FILTRO POR FECHAS
    // -------------------------------------------------------
    @GetMapping("/filtrar/fecha")
    public String filtrarPorFecha(
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            Model model) {

        LocalDateTime f1 = LocalDateTime.parse(desde);
        LocalDateTime f2 = LocalDateTime.parse(hasta);

        model.addAttribute("usuarios", usuarioService.findByCreatedBetween(f1, f2));
        return "usuario/lista";
    }

    // -------------------------------------------------------
    // ESTADÍSTICAS
    // -------------------------------------------------------
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("activos", usuarioService.countActivos());
        model.addAttribute("inactivos", usuarioService.countInactivos());
        model.addAttribute("usuarios", usuarioService.findAllOrderByCreatedDesc());

        return "usuario/estadisticas";
    }
}
