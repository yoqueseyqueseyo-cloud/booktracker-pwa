package booktracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Setter
@Getter
public class LibroDTO {

    private String portadaDTO;
    private String tituloDTO;
    private Integer paginasDTO;
    private Integer sagaOrdenDTO;
    private String sagaTipoDTO;
    private String sagaNombreDTO;
    private String formatoDTO;
    private String estadoDTO;
    private BigDecimal puntuacionDTO;
    private LocalDate fecha_inicioDTO;
    private LocalDate fecha_finDTO;

    private Long autorIdDTO;
    private Long editorialIdDTO;


    // GETTERS

    // SETTERS

}

