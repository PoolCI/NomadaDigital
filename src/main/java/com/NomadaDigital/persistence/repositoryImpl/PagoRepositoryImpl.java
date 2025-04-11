package com.NomadaDigital.persistence.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.domain.repository.ReservaRepository;
import com.NomadaDigital.persistence.entity.Reserva;
import com.NomadaDigital.persistence.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.NomadaDigital.domain.dto.PagoDTO;
import com.NomadaDigital.domain.repository.PagoRepository;
import com.NomadaDigital.persistence.crud.PagoCrudRepository;
import com.NomadaDigital.persistence.entity.Comentario;
import com.NomadaDigital.persistence.entity.Pago;
import com.NomadaDigital.persistence.entity.Pago.EstadoPago;
import com.NomadaDigital.persistence.mapper.PagoMapper;

@Repository
public class PagoRepositoryImpl implements PagoRepository{
	
	@Autowired
	private PagoCrudRepository pagoCrudRepository;
	
	@Autowired
	private PagoMapper pagoMapper;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ReservaMapper reservaMapper;

	@Override
	public Iterable<PagoDTO> findAll() {
		Iterable<Pago> pagos = pagoCrudRepository.findAll();
		return ((List<Pago>) pagos ).stream().map(pagoMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<PagoDTO> findById(Long id) {
		Optional<Pago> pagos = pagoCrudRepository.findById(id);
		return pagos.map(pagoMapper::toDto);
	}

	@Override
	public PagoDTO save(PagoDTO pagoDTO) {
		// Buscar la reserva asociada
		ReservaDTO reservaDTO = reservaRepository.findById(pagoDTO.getReservaId())
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		Reserva reserva = reservaMapper.toEntity(reservaDTO);

		// Convertir DTO a entidad
		Pago pago = pagoMapper.toEntity(pagoDTO);

		// Asignar la reserva y establecer estado inicial
		pago.setReserva(reserva);
		pago.setEstado(EstadoPago.PENDIENTE);

		// Guardar la entidad en la base de datos
		Pago pagoGuardado = pagoCrudRepository.save(pago);

		// Convertir de vuelta a DTO antes de retornar
		return pagoMapper.toDto(pagoGuardado);
	}


	@Override
	public PagoDTO update(PagoDTO pagoDTO) {
		// Buscar el pago existente en la base de datos
		Pago pagoExistente = pagoCrudRepository.findById(pagoDTO.getId())
				.orElseThrow(() -> new IllegalArgumentException("El registro no existe"));

		// Convertir DTO a entidad sin perder la relación con la reserva
		Pago pagoActualizado = pagoMapper.toEntity(pagoDTO);
		pagoActualizado.setReserva(pagoExistente.getReserva()); // Mantener la reserva original

		// Guardar la entidad actualizada
		Pago pagoGuardado = pagoCrudRepository.save(pagoActualizado);

		// Convertir y devolver el DTO actualizado
		return pagoMapper.toDto(pagoGuardado);
	}

	@Override
	public void deleteById(Long id) {
		if(existsById(id)) {
			pagoCrudRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("El registro no existe");
		}	
	}

	@Override
	public boolean existsById(Long id) {
		return pagoCrudRepository.existsById(id);
	}

	@Override
	public long count() {
		return pagoCrudRepository.count();
	}

	@Override
	public Iterable<PagoDTO> findByReservaId(Long reservaId) {
		Iterable<Pago> pagos = pagoCrudRepository.findByReservaId(reservaId);
		return ((List<Pago>) pagos ).stream().map(pagoMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<PagoDTO> findByNumeroTransaccion(String numeroTransaccion) {
		Optional<Pago> pagos = pagoCrudRepository.findByNumeroTransaccion(numeroTransaccion);
		return pagos.map(pagoMapper::toDto);
	}

	@Override
	public Iterable<PagoDTO> findByEstado(EstadoPago estado) {
		Iterable<Pago> pagos = pagoCrudRepository.findByEstado(estado);
		return ((List<Pago>) pagos ).stream().map(pagoMapper::toDto).collect(Collectors.toList());
	}
}
