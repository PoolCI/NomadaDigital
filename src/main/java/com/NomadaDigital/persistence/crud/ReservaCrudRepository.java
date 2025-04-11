package com.NomadaDigital.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.NomadaDigital.persistence.entity.Reserva;
import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;

public interface ReservaCrudRepository extends CrudRepository<Reserva, Long>  {

    // Obtener reservas por ID de cliente
	Iterable<Reserva> findByClienteId(Long clienteId);

    // Obtener reservas por ID de viaje
	Iterable<Reserva> findByViajeId(Long viajeId);

    // Obtener reservas por estado
	Iterable<Reserva> findByEstado(EstadoReserva estado);
}
