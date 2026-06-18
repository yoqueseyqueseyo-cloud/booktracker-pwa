package booktracker.controllers;

import booktracker.entities.Autor;
import booktracker.repositories.AutorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public List<Autor> getAutores() {
        return autorRepository.findAll();
    }

    @PostMapping
    public Autor crearAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/{id}")
    public Autor actualizarAutor(@PathVariable Long id, @RequestBody Autor datos) {
        Autor autor = autorRepository.findById(id).orElse(null);
        if (autor == null) return null;

        autor.setName(datos.getName());

        return autorRepository.save(autor);
    }

    @DeleteMapping("/{id}")
    public void borrarAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
    }


}
