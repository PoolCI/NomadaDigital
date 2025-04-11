package com.NomadaDigital.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.NomadaDigital.persistence.entity.Pago.EstadoPago;
import com.NomadaDigital.persistence.entity.Pago.MetodoPago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private Long id;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;
    private String numeroTransaccion;
    private EstadoPago estado;
    private Long reservaId;
}
