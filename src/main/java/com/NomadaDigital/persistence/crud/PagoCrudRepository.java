package com.NomadaDigital.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.NomadaDigital.persistence.entity.Pago;
import com.NomadaDigital.persistence.entity.Pago.EstadoPago;

public interface PagoCrudRepository extends CrudRepository<Pago, Long>  {

    // Obtener pagos por ID de reserva
	Iterable<Pago> findByReservaId(Long reservaId);

    // Obtener pago por número de transacción
    Optional<Pago> findByNumeroTransaccion(String numeroTransaccion);

    // Obtener pagos por estado
    Iterable<Pago> findByEstado(EstadoPago estado);
}
