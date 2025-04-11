package com.NomadaDigital.domain.repository;

import java.util.Optional;

import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;

public interface ReservaRepository {

    // Consultar todos los registros
    Iterable<ReservaDTO> findAll(); 
    
    // Consultar por ID
    Optional<ReservaDTO> findById(Long id); 
    
    // Guardar
    ReservaDTO save(ReservaDTO reservaDTO);
    
    // Actualizar
    ReservaDTO update(ReservaDTO reservaDTO);
    
    // Eliminar
    void deleteById(Long id); 

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();
    
    // Consultar por ID del cliente
    Iterable<ReservaDTO> findByClienteId(Long clienteId);
    
    // Consultar por ID del viaje
    Iterable<ReservaDTO> findByViajeId(Long viajeId);
    
    // Consultar por estadoReserva
    Iterable<ReservaDTO> findByEstado(EstadoReserva estado);
}
