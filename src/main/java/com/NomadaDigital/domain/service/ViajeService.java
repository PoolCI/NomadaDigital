package com.NomadaDigital.domain.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NomadaDigital.domain.dto.ViajeDTO;
import com.NomadaDigital.domain.repository.ViajeRepository;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // Obtener todos los viajes
    public Iterable<ViajeDTO> findAll() {
        return viajeRepository.findAll();
    }

    // Obtener un viaje por ID
    public ViajeDTO findById(Long id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
    }

    // Guardar un nuevo viaje
    public ViajeDTO save(ViajeDTO viajeDTO) {
        return viajeRepository.save(viajeDTO);
    }

    // Actualizar un viaje existente
    public ViajeDTO update(ViajeDTO viajeDTO) {
        return viajeRepository.update(viajeDTO);
    }

    // Eliminar un viaje por ID
    public void deleteById(Long id) {
        viajeRepository.deleteById(id);
    }

    // Verificar si un viaje existe por ID
    public boolean existsById(Long id) {
        return viajeRepository.existsById(id);
    }

    // Contar el total de viajes registrados
    public long count() {
        return viajeRepository.count();
    }

    // Obtener viajes por destino
    public Iterable<ViajeDTO> findByDestino(String destino) {
        return viajeRepository.findByDestino(destino);
    }

    // Obtener viajes dentro de un rango de fechas
    public Iterable<ViajeDTO> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return viajeRepository.findByFechaInicioBetween(fechaInicio, fechaFin);
    }

    // Obtener solo los viajes activos
    public Iterable<ViajeDTO> findByActivoTrue() {
        return viajeRepository.findByActivoTrue();
    }

    // Obtener solo por codigo unico
    public ViajeDTO findByCodigoUnico(String codigoUnico) {
        return viajeRepository.findByCodigoUnico(codigoUnico)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
    }
}
