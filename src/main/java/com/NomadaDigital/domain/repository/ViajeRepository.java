package com.NomadaDigital.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import com.NomadaDigital.domain.dto.ViajeDTO;

public interface ViajeRepository {

	 // Consultar todos los registros
   Iterable<ViajeDTO> findAll();  
   
   // Consultar por ID
   Optional<ViajeDTO> findById(Long id);  
   
   // Guardar
   ViajeDTO save(ViajeDTO viajeDTO);
   
   // Actualizar
   ViajeDTO update(ViajeDTO viajeDTO); 
   
   // Eliminar
   void deleteById(Long id);  
   
   // Validar si existe por ID
   boolean existsById(Long id);

   // Contar todos los registros
   long count();
   
   // Consultar por Destino
   Iterable<ViajeDTO> findByDestino(String destino); 
   
   // Consultar por FechaIicio entre FehcaFin
   Iterable<ViajeDTO> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
   
   // Consultar por Estado = Activo
   Iterable<ViajeDTO> findByActivoTrue();

   // Consultar por Codigo Unico
   Optional<ViajeDTO> findByCodigoUnico(String codigoUnico);
}
