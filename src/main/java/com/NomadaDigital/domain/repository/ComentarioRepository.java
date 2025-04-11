package com.NomadaDigital.domain.repository;

import java.util.Optional;

import com.NomadaDigital.domain.dto.ComentarioDTO;

public interface ComentarioRepository {
	
    // Consultar todos los registros
    Iterable<ComentarioDTO> findAll(); 
    
    // Consultar por ID
    Optional<ComentarioDTO> findById(Long id); 
    
    // Guardar
    ComentarioDTO save(ComentarioDTO comentarioDTO);
    
    // Actualizar
    ComentarioDTO update(ComentarioDTO comentarioDTO);
    
    // Eliminar
    void deleteById(Long id);
    
    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();
    
    // Consultar por ID del viaje
    Iterable<ComentarioDTO> findByViajeId(Long viajeId);
    
    // Consultar por ID del Cliente    
    Iterable<ComentarioDTO> findByClienteId(Long clienteId);
    
    // Consultar del promedio de calificaciones mediante el ID del viajero
    Optional<Double> getCalificacionPromedioByViajeId(Long viajeId); 

}
