package com.NomadaDigital.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.NomadaDigital.domain.dto.ClienteDTO;
import com.NomadaDigital.persistence.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	// Posible cambio en el DTO para agregar las listas y por ende mappeo de ellas
	
	@Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    ClienteDTO toDto(Cliente cliente);
    
    @InheritInverseConfiguration
    Cliente toEntity(ClienteDTO clienteDTO);

}
