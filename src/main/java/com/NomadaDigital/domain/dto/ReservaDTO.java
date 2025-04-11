package com.NomadaDigital.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Long id;
    private LocalDateTime fechaReserva;
    private LocalDate fechaViaje;
    private Integer cantidadPersonas;
    private EstadoReserva estado;
    private Long viajeId;
    private Long clienteId;
}
