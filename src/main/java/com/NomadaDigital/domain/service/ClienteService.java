package com.NomadaDigital.domain.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NomadaDigital.domain.dto.ClienteDTO;
import com.NomadaDigital.domain.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    public Iterable<ClienteDTO> findAll() {
        return clienteRepository.findAll();
    }

    // Obtener un cliente por ID
    public ClienteDTO findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Guardar un nuevo cliente
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return clienteRepository.save(clienteDTO);
    }

    // Actualizar un cliente existente
    public ClienteDTO update(ClienteDTO clienteDTO) {
        return clienteRepository.update(clienteDTO);
    }

    // Eliminar un cliente por ID
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    // Verificar si un cliente existe por ID
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    // Contar el total de clientes registrados
    public long count() {
        return clienteRepository.count();
    }

    // Buscar cliente por email
    public Optional<ClienteDTO> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
