package booktracker.controllers;

import booktracker.dto.LibroDTO;
import booktracker.entities.*;
import booktracker.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final EditorialRepository editorialRepository;
    private final UsuarioRepository usuarioRepository;

    public LibroController(
            LibroRepository libroRepository,
            AutorRepository autorRepository,
            EditorialRepository editorialRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.editorialRepository = editorialRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getById(@PathVariable Long id) {
        return libroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro crearLibro(@RequestBody LibroDTO dto) {

        Libro libro = new Libro();

        libro.setPortada(dto.getPortadaDTO());
        libro.setTitulo(dto.getTituloDTO());
        libro.setAutor(autorRepository.findById(dto.getAutorIdDTO()).orElse(null));
        libro.setPaginas(dto.getPaginasDTO());
        libro.setFormato(dto.getFormatoDTO());
        libro.setEstado(dto.getEstadoDTO());
        libro.setEditorial(editorialRepository.findById(dto.getEditorialIdDTO()).orElse(null));
        libro.setPuntuacion(dto.getPuntuacionDTO());
        libro.setFecha_inicio(dto.getFecha_inicioDTO());
        libro.setFecha_fin(dto.getFecha_finDTO());
        libro.setSagaTipo(dto.getSagaTipoDTO());
        libro.setSagaOrden(dto.getSagaOrdenDTO());
        libro.setSagaNombre(dto.getSagaNombreDTO());
        return libroRepository.save(libro);
    }


    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody LibroDTO dto) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setPortada(dto.getPortadaDTO());
        libro.setTitulo(dto.getTituloDTO());
        libro.setAutor(autorRepository.findById(dto.getAutorIdDTO()).orElse(null));
        libro.setPaginas(dto.getPaginasDTO());
        libro.setFormato(dto.getFormatoDTO());
        libro.setEstado(dto.getEstadoDTO());
        libro.setEditorial(editorialRepository.findById(dto.getEditorialIdDTO()).orElse(null));
        libro.setPuntuacion(dto.getPuntuacionDTO());
        libro.setFecha_inicio(dto.getFecha_inicioDTO());
        libro.setFecha_fin(dto.getFecha_finDTO());
        libro.setSagaTipo(dto.getSagaTipoDTO());
        libro.setSagaOrden(dto.getSagaOrdenDTO());

        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void borrarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }

}