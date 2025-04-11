package com.NomadaDigital.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.NomadaDigital.persistence.entity.Comentario;

public interface ComentarioCrudRepository extends CrudRepository<Comentario, Long>   {
	
    // Obtener comentarios por ID de viaje
	Iterable<Comentario> findByViajeId(Long viajeId);

    // Obtener comentarios por ID de cliente
	Iterable<Comentario> findByClienteId(Long clienteId);

    // Obtener promedio de calificaciones de un viaje
    @Query("SELECT AVG(c.calificacion) FROM Comentario c WHERE c.viaje.id = :viajeId")
    Optional<Double> getCalificacionPromedioByViajeId(Long viajeId);

}
