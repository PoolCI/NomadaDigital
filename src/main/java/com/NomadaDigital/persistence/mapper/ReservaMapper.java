package com.NomadaDigital.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.persistence.entity.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "fechaReserva", target = "fechaReserva")
    @Mapping(source = "fechaViaje", target = "fechaViaje")
    @Mapping(source = "cantidadPersonas", target = "cantidadPersonas")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "viaje.id", target = "viajeId")
    @Mapping(source = "cliente.id", target = "clienteId")
    ReservaDTO toDto(Reserva reserva);
    
    @InheritInverseConfiguration
    @Mapping(target = "viaje", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Reserva toEntity(ReservaDTO reservaDTO);
}
