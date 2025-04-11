package com.NomadaDigital.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.NomadaDigital.domain.dto.ComentarioDTO;
import com.NomadaDigital.persistence.entity.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

	
    @Mapping(source = "id", target = "id")
    @Mapping(source = "texto", target = "texto")
    @Mapping(source = "calificacion", target = "calificacion")
    @Mapping(source = "fechaComentario", target = "fechaComentario")
    @Mapping(source = "viaje.id", target = "viajeId")
    @Mapping(source = "cliente.id", target = "clienteId") 
	ComentarioDTO toDto(Comentario comentario);
	
    @InheritInverseConfiguration
    @Mapping(target = "viaje", ignore = true) 
    @Mapping(target = "cliente", ignore = true) 
	Comentario toEntity (ComentarioDTO comentarioDTO);
}
