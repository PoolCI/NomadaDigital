package com.NomadaDigital.persistence.repositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.NomadaDigital.domain.dto.ViajeDTO;
import com.NomadaDigital.domain.repository.ViajeRepository;
import com.NomadaDigital.persistence.crud.ViajeCrudRepository;
import com.NomadaDigital.persistence.entity.Reserva;
import com.NomadaDigital.persistence.entity.Viaje;
import com.NomadaDigital.persistence.mapper.ViajeMapper;

@Repository
public class ViajeRepositoryImpl implements ViajeRepository{
	
	@Autowired
	private ViajeCrudRepository viajeCrudRepository;
	
	@Autowired
	private ViajeMapper viajeMapper;

	@Override
	public Iterable<ViajeDTO> findAll() {
		Iterable<Viaje> viajes = viajeCrudRepository.findAll();
		return ((List<Viaje>) viajes ).stream().map(viajeMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ViajeDTO> findById(Long id) {
		Optional<Viaje> viajes = viajeCrudRepository.findById(id);
		return viajes.map(viajeMapper::toDto);
	}

	@Override
	public ViajeDTO save(ViajeDTO viajeDTO) {
		Viaje viaje = viajeMapper.toEntity(viajeDTO);
		if (findByCodigoUnico(viaje.getCodigoUnico()).isPresent()) {
			throw new IllegalArgumentException("El código único del viaje ya existe");
		}
		Viaje saveViaje = viajeCrudRepository.save(viaje);
		return viajeMapper.toDto(saveViaje);
	}

	@Override
	public ViajeDTO update(ViajeDTO viajeDTO) {
		Viaje viaje = viajeMapper.toEntity(viajeDTO);
		if(existsById(viaje.getId())) {
			Viaje updateReserva = viajeCrudRepository.save(viaje);
			return viajeMapper.toDto(updateReserva);
		}
		throw new IllegalArgumentException("El registro no existe");
	}

	@Override
	public void deleteById(Long id) {
		if(existsById(id)) {
			viajeCrudRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("El registro no existe");
		}
		
	}

	@Override
	public boolean existsById(Long id) {
		return viajeCrudRepository.existsById(id);
	}

	@Override
	public long count() {
		return viajeCrudRepository.count();
	}

	@Override
	public Iterable<ViajeDTO> findByDestino(String destino) {
		Iterable<Viaje> viajes = viajeCrudRepository.findByDestino(destino);
		return ((List<Viaje>) viajes ).stream().map(viajeMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<ViajeDTO> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin) {
		Iterable<Viaje> viajes = viajeCrudRepository.findByFechaInicioBetween(fechaInicio, fechaFin );
		return ((List<Viaje>) viajes ).stream().map(viajeMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<ViajeDTO> findByActivoTrue() {
		Iterable<Viaje> viajes = viajeCrudRepository.findByActivoTrue();
		return ((List<Viaje>) viajes ).stream().map(viajeMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ViajeDTO> findByCodigoUnico(String codigoUnico) {
		Optional<Viaje> viajes = viajeCrudRepository.findByCodigoUnico(codigoUnico);
		return viajes.map(viajeMapper::toDto);
	}

}
