package com.NomadaDigital.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.NomadaDigital.domain.dto.PagoDTO;
import com.NomadaDigital.persistence.entity.Pago;

@Mapper(componentModel = "spring")
public interface PagoMapper {
	
    @Mapping(source = "id", target = "id")
    @Mapping(source = "monto", target = "monto")
    @Mapping(source = "fechaPago", target = "fechaPago")
    @Mapping(source = "metodoPago", target = "metodoPago")
    @Mapping(source = "numeroTransaccion", target = "numeroTransaccion")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "reserva.id", target = "reservaId")
    PagoDTO toDto(Pago pago);
    
    @InheritInverseConfiguration
    @Mapping(target = "reserva", ignore = true)
    Pago toEntity(PagoDTO pagoDTO);
}
