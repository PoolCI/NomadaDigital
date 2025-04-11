package com.NomadaDigital.persistence.crud;

import java.time.LocalDate;
import java.util.Optional;

import com.NomadaDigital.persistence.entity.Pago;
import org.springframework.data.repository.CrudRepository;

import com.NomadaDigital.persistence.entity.Viaje;

public interface ViajeCrudRepository extends CrudRepository<Viaje, Long>   {

    // Obtener viajes por destino
	Iterable<Viaje> findByDestino(String destino);

    // Obtener viajes dentro de un rango de fechas de inicio
	Iterable<Viaje> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Obtener viajes activos
	Iterable<Viaje> findByActivoTrue();

    // Obtener viajes con codigo unico
    Optional<Viaje>  findByCodigoUnico(String codigoUnico);
}
