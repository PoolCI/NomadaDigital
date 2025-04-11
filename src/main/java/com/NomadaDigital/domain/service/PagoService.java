package com.NomadaDigital.domain.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NomadaDigital.domain.dto.PagoDTO;
import com.NomadaDigital.domain.repository.PagoRepository;
import com.NomadaDigital.persistence.entity.Pago.EstadoPago;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // Obtener todos los pagos
    public Iterable<PagoDTO> findAll() {
        return pagoRepository.findAll();
    }

    // Obtener un pago por ID
    public PagoDTO findById(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    // Guardar un nuevo pago
    public PagoDTO save(PagoDTO pagoDTO) {return pagoRepository.save(pagoDTO);}

    // Actualizar un pago existente
    public PagoDTO update(PagoDTO pagoDTO) {
        return pagoRepository.update(pagoDTO);
    }

    // Eliminar un pago por ID
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    // Verificar si un pago existe por ID
    public boolean existsById(Long id) {
        return pagoRepository.existsById(id);
    }

    // Contar el total de pagos registrados
    public long count() {
        return pagoRepository.count();
    }

    // Obtener pagos por reserva
    public Iterable<PagoDTO> findByReservaId(Long reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }

    // Obtener pago por número de transacción
    public Optional<PagoDTO> findByNumeroTransaccion(String numeroTransaccion) {
        return pagoRepository.findByNumeroTransaccion(numeroTransaccion);
    }

    // Obtener pagos por estado (ej. Aprobado, Pendiente, Rechazado)
    public Iterable<PagoDTO> findByEstado(EstadoPago estado) {
        return pagoRepository.findByEstado(estado);
    }
}
