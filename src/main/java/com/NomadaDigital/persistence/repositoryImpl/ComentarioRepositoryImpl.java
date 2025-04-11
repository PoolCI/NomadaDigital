package com.NomadaDigital.persistence.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.NomadaDigital.persistence.crud.ClienteCrudRepository;
import com.NomadaDigital.persistence.crud.ViajeCrudRepository;
import com.NomadaDigital.persistence.entity.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.NomadaDigital.domain.dto.ComentarioDTO;
import com.NomadaDigital.domain.repository.ComentarioRepository;
import com.NomadaDigital.persistence.crud.ComentarioCrudRepository;
import com.NomadaDigital.persistence.entity.Cliente;
import com.NomadaDigital.persistence.entity.Comentario;
import com.NomadaDigital.persistence.mapper.ComentarioMapper;

@Repository
public class ComentarioRepositoryImpl implements ComentarioRepository {
	
	@Autowired
	private ComentarioCrudRepository comentarioCrudRepository;

	@Autowired
	private ClienteCrudRepository clienteCrudRepository;

	@Autowired
	private ViajeCrudRepository viajeCrudRepository;

	@Autowired
	private ComentarioMapper comentarioMapper;

	@Override
	public Iterable<ComentarioDTO> findAll() {
		Iterable<Comentario> comentarios = comentarioCrudRepository.findAll();
		return ((List<Comentario>) comentarios ).stream().map(comentarioMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ComentarioDTO> findById(Long id) {
		Optional<Comentario> comentarios = comentarioCrudRepository.findById(id);
		return comentarios.map(comentarioMapper::toDto);
	}

	@Override
	public ComentarioDTO save(ComentarioDTO comentarioDTO) {
		Comentario comentario = comentarioMapper.toEntity(comentarioDTO);

		if (comentario.getId() != null) {
			throw new IllegalArgumentException("El comentario ya existe");
		}

		// Obtener y asignar el Cliente
		Long clienteId = comentarioDTO.getClienteId();
		Cliente cliente = clienteCrudRepository.findById(clienteId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + clienteId));
		comentario.setCliente(cliente);

		// Obtener y asignar el Viaje
		Long viajeId = comentarioDTO.getViajeId();
		Viaje viaje = viajeCrudRepository.findById(viajeId)
				.orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado con ID: " + viajeId));
		comentario.setViaje(viaje);

		Comentario savedComentario = comentarioCrudRepository.save(comentario);
		return comentarioMapper.toDto(savedComentario);
	}

	@Override
	public ComentarioDTO update(ComentarioDTO comentarioDTO) {
		if (comentarioDTO.getId() == null || !existsById(comentarioDTO.getId())) {
			throw new IllegalArgumentException("El registro no existe");
		}

		// Obtener el comentario existente
		Comentario comentarioExistente = comentarioCrudRepository.findById(comentarioDTO.getId())
				.orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado"));

		// Mapear los nuevos valores desde el DTO sin perder relaciones
		comentarioExistente.setTexto(comentarioDTO.getTexto());
		comentarioExistente.setCalificacion(comentarioDTO.getCalificacion());

		// Obtener y asignar el Cliente si cambió
		if (comentarioDTO.getClienteId() != null && !comentarioDTO.getClienteId().equals(comentarioExistente.getCliente().getId())) {
			Cliente cliente = clienteCrudRepository.findById(comentarioDTO.getClienteId())
					.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + comentarioDTO.getClienteId()));
			comentarioExistente.setCliente(cliente);
		}

		// Obtener y asignar el Viaje si cambió
		if (comentarioDTO.getViajeId() != null && !comentarioDTO.getViajeId().equals(comentarioExistente.getViaje().getId())) {
			Viaje viaje = viajeCrudRepository.findById(comentarioDTO.getViajeId())
					.orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado con ID: " + comentarioDTO.getViajeId()));
			comentarioExistente.setViaje(viaje);
		}

		// Guardar la actualización
		Comentario updateComentario = comentarioCrudRepository.save(comentarioExistente);
		return comentarioMapper.toDto(updateComentario);
	}

	@Override
	public void deleteById(Long id) {
		if(existsById(id)) {
			comentarioCrudRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("El registro no existe");
		}	
	}

	@Override
	public boolean existsById(Long id) {
		return comentarioCrudRepository.existsById(id);
	}

	@Override
	public long count() {
		return comentarioCrudRepository.count();
	}

	@Override
	public Iterable<ComentarioDTO> findByViajeId(Long viajeId) {
		Iterable<Comentario> comentarios = comentarioCrudRepository.findByViajeId(viajeId);
		return ((List<Comentario>) comentarios ).stream().map(comentarioMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<ComentarioDTO> findByClienteId(Long clienteId) {
		Iterable<Comentario> comentarios = comentarioCrudRepository.findByClienteId(clienteId);
		return ((List<Comentario>) comentarios ).stream().map(comentarioMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<Double> getCalificacionPromedioByViajeId(Long viajeId) {
		return comentarioCrudRepository.getCalificacionPromedioByViajeId(viajeId);
	}

}
