package com.NomadaDigital.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private Long id;
    private String codigoUnico;
    private String destino;
    private Integer duracionDias;
    private BigDecimal precio;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer capacidadMaxima;
    private Integer lugaresDisponibles;
    private Boolean activo;
}
