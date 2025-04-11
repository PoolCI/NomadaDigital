package com.NomadaDigital.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
    private Long id;
    private String texto;
    private Integer calificacion;
    private LocalDateTime fechaComentario;
    private Long viajeId;
    private Long clienteId;
}
