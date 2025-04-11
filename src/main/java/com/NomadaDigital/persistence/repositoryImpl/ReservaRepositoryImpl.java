package com.NomadaDigital.persistence.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.NomadaDigital.persistence.crud.ClienteCrudRepository;
import com.NomadaDigital.persistence.crud.ViajeCrudRepository;
import com.NomadaDigital.persistence.entity.Cliente;
import com.NomadaDigital.persistence.entity.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.domain.repository.ReservaRepository;
import com.NomadaDigital.persistence.crud.ReservaCrudRepository;
import com.NomadaDigital.persistence.entity.Pago;
import com.NomadaDigital.persistence.entity.Reserva;
import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;
import com.NomadaDigital.persistence.mapper.ReservaMapper;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository{

	@Autowired
	private ReservaCrudRepository reservaCrudRepository;

	@Autowired
	private ReservaMapper reservaMapper;

	@Autowired
	private ClienteCrudRepository clienteCrudRepository;

	@Autowired
	private ViajeCrudRepository viajeCrudRepository;

	@Override
	public Iterable<ReservaDTO> findAll() {
		Iterable<Reserva> reservas = reservaCrudRepository.findAll();
		return ((List<Reserva>) reservas ).stream().map(reservaMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ReservaDTO> findById(Long id) {
		Optional<Reserva> reservas = reservaCrudRepository.findById(id);
		return reservas.map(reservaMapper::toDto);
	}

	@Override
	public ReservaDTO save(ReservaDTO reservaDTO) {
		// Validar campos obligatorios de la reserva
		if (reservaDTO.getClienteId() == null) {
			throw new IllegalArgumentException("El ID de cliente es obligatorio");
		}
		if (reservaDTO.getViajeId() == null) {
			throw new IllegalArgumentException("El ID de viaje es obligatorio");
		}
		if (reservaDTO.getFechaReserva() == null) {
			throw new IllegalArgumentException("La fecha de reserva es obligatoria");
		}

		// Obtener y validar el viaje relacionado
		Viaje viaje = viajeCrudRepository.findById(reservaDTO.getViajeId())
				.orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

		// Validar que el viaje tenga capacidadMaxima válida
		if (viaje.getCapacidadMaxima() == null || viaje.getCapacidadMaxima() <= 0) {
			throw new IllegalArgumentException("El viaje no tiene una capacidad máxima válida");
		}

		// Convertir a entidad y asignar relaciones
		Reserva reserva = reservaMapper.toEntity(reservaDTO);
		Cliente cliente = clienteCrudRepository.findById(reservaDTO.getClienteId())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

		reserva.setCliente(cliente);
		reserva.setViaje(viaje);

		// Estado por defecto
		if (reserva.getEstado() == null) {
			reserva.setEstado(EstadoReserva.PENDIENTE);
		}

		Reserva savedReserva = reservaCrudRepository.save(reserva);
		return reservaMapper.toDto(savedReserva);
	}

	@Override
	public ReservaDTO update(ReservaDTO reservaDTO) {
		if (!existsById(reservaDTO.getId())) {
			throw new IllegalArgumentException("El registro no existe");
		}

		Reserva reserva = reservaMapper.toEntity(reservaDTO);

		// Obtener y validar Cliente
		Cliente cliente = clienteCrudRepository.findById(reservaDTO.getClienteId())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		reserva.setCliente(cliente);

		// Obtener y validar Viaje
		Viaje viaje = viajeCrudRepository.findById(reservaDTO.getViajeId())
				.orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));
		reserva.setViaje(viaje);

		// Asegurar que el estado no sea nulo
		if (reserva.getEstado() == null) {
			reserva.setEstado(EstadoReserva.PENDIENTE);
		}

		Reserva updateReserva = reservaCrudRepository.save(reserva);
		return reservaMapper.toDto(updateReserva);
	}

	@Override
	public void deleteById(Long id) {
		if(existsById(id)) {
			reservaCrudRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("El registro no existe");
		}
	}

	@Override
	public boolean existsById(Long id) {
		return reservaCrudRepository.existsById(id);
	}

	@Override
	public long count() {
		return reservaCrudRepository.count();
	}

	@Override
	public Iterable<ReservaDTO> findByClienteId(Long clienteId) {
		Iterable<Reserva> reservas = reservaCrudRepository.findByClienteId(clienteId);
		return ((List<Reserva>) reservas ).stream().map(reservaMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<ReservaDTO> findByViajeId(Long viajeId) {
		Iterable<Reserva> reservas = reservaCrudRepository.findByViajeId(viajeId);
		return ((List<Reserva>) reservas ).stream().map(reservaMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<ReservaDTO> findByEstado(EstadoReserva estado) {
		Iterable<Reserva> reservas = reservaCrudRepository.findByEstado(estado);
		return ((List<Reserva>) reservas ).stream().map(reservaMapper::toDto).collect(Collectors.toList());
	}

}
