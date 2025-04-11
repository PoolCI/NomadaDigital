package com.NomadaDigital.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NomadaDigital.domain.dto.ComentarioDTO;
import com.NomadaDigital.domain.repository.ComentarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // Obtener todos los comentarios
    public Iterable<ComentarioDTO> findAll() {
        return comentarioRepository.findAll();
    }

    // Obtener un comentario por ID
    public ComentarioDTO findById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
    }

    // Guardar un nuevo comentario
    public ComentarioDTO save(ComentarioDTO comentarioDTO) {
        return comentarioRepository.save(comentarioDTO);
    }

    // Actualizar un comentario existente
    public ComentarioDTO update(ComentarioDTO comentarioDTO) {
        return comentarioRepository.update(comentarioDTO);
    }

    // Eliminar un comentario por ID
    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }

    // Verificar si un comentario existe por ID
    public boolean existsById(Long id) {
        return comentarioRepository.existsById(id);
    }

    // Contar el total de comentarios registrados
    public long count() {
        return comentarioRepository.count();
    }

    // Obtener comentarios por ID de viaje
    public Iterable<ComentarioDTO> findByViajeId(Long viajeId) {
        return comentarioRepository.findByViajeId(viajeId);
    }

    // Obtener comentarios por ID de cliente
    public Iterable<ComentarioDTO> findByClienteId(Long clienteId) {
        return comentarioRepository.findByClienteId(clienteId);
    }

    // Obtener el promedio de calificaciones de un viaje
    public Double getCalificacionPromedioByViajeId(Long viajeId) {
        return comentarioRepository.getCalificacionPromedioByViajeId(viajeId)
                .orElse(0.0); // Si no hay comentarios, retorna 0 en lugar de null
    }
}
