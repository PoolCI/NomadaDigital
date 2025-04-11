package com.NomadaDigital.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.domain.repository.ReservaRepository;
import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Obtener todas las reservas
    public Iterable<ReservaDTO> findAll() {
        return reservaRepository.findAll();
    }

    // Obtener una reserva por ID
    public ReservaDTO findById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    // Guardar una nueva reserva
    public ReservaDTO save(ReservaDTO reservaDTO) {
        return reservaRepository.save(reservaDTO);
    }

    // Actualizar una reserva existente
    public ReservaDTO update(ReservaDTO reservaDTO) {
        return reservaRepository.update(reservaDTO);
    }

    // Eliminar una reserva por ID
    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    // Verificar si una reserva existe por ID
    public boolean existsById(Long id) {
        return reservaRepository.existsById(id);
    }

    // Contar el total de reservas registradas
    public long count() {
        return reservaRepository.count();
    }

    // Obtener reservas por cliente
    public Iterable<ReservaDTO> findByClienteId(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    // Obtener reservas por viaje
    public Iterable<ReservaDTO> findByViajeId(Long viajeId) {
        return reservaRepository.findByViajeId(viajeId);
    }

    // Obtener reservas por estado 
    public Iterable<ReservaDTO> findByEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }
}
