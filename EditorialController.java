package booktracker.controllers;

import booktracker.entities.Editorial;
import booktracker.repositories.EditorialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

    private final EditorialRepository editorialRepository;

    public EditorialController(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @GetMapping
    public List<Editorial> getEditoriales() {
        return editorialRepository.findAll();
    }

    @PostMapping
    public Editorial crearEditorial(@RequestBody Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @PutMapping("/{id}")
    public Editorial actualizarEditorial(@PathVariable Long id, @RequestBody Editorial datos) {
        Editorial editorial = editorialRepository.findById(id).orElse(null);
        if (editorial == null) return null;

        editorial.setName(datos.getName());

        return editorialRepository.save(editorial);
    }

    @DeleteMapping("/{id}")
    public void borrarEditorial(@PathVariable Long id) {
        editorialRepository.deleteById(id);
    }

}
