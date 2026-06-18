package booktracker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "libros")
public class Libro {

    @JoinColumn(columnDefinition ="TEXT")
    private String portada;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String sagaTipo;

    private Integer sagaOrden;


    private String sagaNombre;

    private Integer paginas;

    private String formato;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    private BigDecimal puntuacion;

    private LocalDate fecha_inicio;

    private LocalDate fecha_fin;

    public Libro() {}


}
