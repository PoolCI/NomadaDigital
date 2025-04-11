package com.NomadaDigital.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.NomadaDigital.domain.dto.ViajeDTO;
import com.NomadaDigital.persistence.entity.Viaje;

@Mapper(componentModel = "spring")
public interface ViajeMapper {
	
	// Posible cambio en el DTO para agregar las listas y por ende mappeo de ellas
	
	// Mapeo de Factura a FacturaDTO:
    @Mapping(source = "id", target = "id")
    @Mapping(source = "codigoUnico", target = "codigoUnico")
    @Mapping(source = "destino", target = "destino")
    @Mapping(source = "duracionDias", target = "duracionDias")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "fechaInicio", target = "fechaInicio")
    @Mapping(source = "fechaFin", target = "fechaFin")
    @Mapping(source = "capacidadMaxima", target = "capacidadMaxima")
    @Mapping(source = "lugaresDisponibles", target = "lugaresDisponibles")
    @Mapping(source = "activo", target = "activo")
    ViajeDTO toDto(Viaje viaje);
    
    @InheritInverseConfiguration
    Viaje toEntity(ViajeDTO viajeDTO);
}
