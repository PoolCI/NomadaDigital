package com.NomadaDigital.domain.repository;

import java.util.Optional;

import com.NomadaDigital.domain.dto.ClienteDTO;

public interface ClienteRepository {
	
    // Consultar todos los registros
    Iterable<ClienteDTO> findAll(); 
    
    // Consultar por ID
    Optional<ClienteDTO> findById(Long id); 
    
    // Guardar
    ClienteDTO save(ClienteDTO clienteDTO);
    
    // Actualizar
    ClienteDTO update(ClienteDTO clienteDTO);
    
    // Eliminar
    void deleteById(Long id); 

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();
    
    // Consultar por Email
    Optional<ClienteDTO> findByEmail(String email); 

}
