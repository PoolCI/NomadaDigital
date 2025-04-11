package com.NomadaDigital.domain.repository;

import java.util.Optional;

import com.NomadaDigital.domain.dto.PagoDTO;
import com.NomadaDigital.persistence.entity.Pago.EstadoPago;

public interface PagoRepository {
    // Consultar todos los registros
	Iterable<PagoDTO> findAll();

    // Consultar por ID
    Optional<PagoDTO> findById(Long id);

    // Guardar
    PagoDTO save(PagoDTO pagoDTO);

    // Actualizar
    PagoDTO update(PagoDTO pagoDTO);

    // Eliminar
    void deleteById(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();

    // Consultar los pagos de las reservaciones mediante su ID
    Iterable<PagoDTO> findByReservaId(Long reservaId);

    // Consultar por numeroTransaccion
    Optional<PagoDTO> findByNumeroTransaccion(String numeroTransaccion); // Usa Optional para evitar null

    // Consultar por EstadoPago
    Iterable<PagoDTO> findByEstado(EstadoPago estado);
}
