package com.NomadaDigital.persistence.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.NomadaDigital.domain.dto.ClienteDTO;
import com.NomadaDigital.domain.repository.ClienteRepository;
import com.NomadaDigital.persistence.crud.ClienteCrudRepository;
import com.NomadaDigital.persistence.entity.Cliente;
import com.NomadaDigital.persistence.mapper.ClienteMapper;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository{
	
	@Autowired
	private ClienteCrudRepository clienteCrudRepository;
	
	@Autowired
	private ClienteMapper clienteMapper;

	@Override
	public Iterable<ClienteDTO> findAll() {
		Iterable<Cliente> clientes = clienteCrudRepository.findAll();
		return ((List<Cliente>) clientes ).stream().map(clienteMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ClienteDTO> findById(Long id) {
		Optional<Cliente> clientes = clienteCrudRepository.findById(id);
		return clientes.map(clienteMapper::toDto);
	}

	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) {
		Cliente cliente = clienteMapper.toEntity(clienteDTO);

		if (!clienteCrudRepository.findByEmail(cliente.getEmail()).isPresent()) {
			Cliente saveCliente = clienteCrudRepository.save(cliente);
			return clienteMapper.toDto(saveCliente);
		}

		throw new IllegalArgumentException("El registro con este correo ya existe");
	}

	@Override
	public ClienteDTO update(ClienteDTO clienteDTO) {
		Cliente cliente = clienteMapper.toEntity(clienteDTO);
		if(existsById(cliente.getId())) {
			Cliente updateCliente = clienteCrudRepository.save(cliente);
			return clienteMapper.toDto(updateCliente);
		}
		throw new IllegalArgumentException("El registro no existe");
	}

	@Override
	public void deleteById(Long id) {
		if(existsById(id)) {
			clienteCrudRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("El registro no existe");
		}
	}

	@Override
	public boolean existsById(Long id) {
		return clienteCrudRepository.existsById(id);
	}

	@Override
	public long count() {
		return clienteCrudRepository.count();
	}

	@Override
	public Optional<ClienteDTO> findByEmail(String email) {
		Optional<Cliente> clientes = clienteCrudRepository.findByEmail(email);
		return clientes.map(clienteMapper::toDto);
	}
}
