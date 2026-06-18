package booktracker.controllers;

import booktracker.entities.Usuario;
import booktracker.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datos) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) return null;

        usuario.setUsername(datos.getUsername());
        usuario.setPassword_hash(datos.getPassword_hash());
        usuario.setCreated_at(datos.getCreated_at());

        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }


}
