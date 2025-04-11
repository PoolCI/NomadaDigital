package com.NomadaDigital.web.controller;

import com.NomadaDigital.domain.dto.ComentarioDTO;
import com.NomadaDigital.domain.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Obtener todos los comentarios
    @Operation(summary = "Obtener todos los comentarios", description = "Retorna una lista de todos los comentarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ComentarioDTO>> getAllComentarios() {
        return new ResponseEntity<>(comentarioService.findAll(), HttpStatus.OK);
    }

    // Obtener comentario por ID
    @Operation(summary = "Obtener comentario por ID", description = "Retorna el comentario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario encontrado"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(comentarioService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar un nuevo comentario
    @Operation(summary = "Guardar nuevo comentario", description = "Guarda un nuevo comentario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el comentario")
    })
    @PostMapping("/save")
    public ResponseEntity<ComentarioDTO> createComentario(@RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.save(comentarioDTO), HttpStatus.CREATED);
    }

    // Actualizar un comentario existente
    @Operation(summary = "Actualizar comentario", description = "Actualiza los datos de un comentario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el comentario"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) {
        comentarioDTO.setId(id); // Asegurar que el ID es el correcto
        return ResponseEntity.ok(comentarioService.update(comentarioDTO));
    }

    // Eliminar un comentario por ID
    @Operation(summary = "Eliminar comentario", description = "Elimina el comentario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Contar el número total de comentarios
    @Operation(summary = "Contar comentarios", description = "Retorna el número total de comentarios registrados")
    @ApiResponse(responseCode = "200", description = "Número total de comentarios")
    @GetMapping("/count")
    public ResponseEntity<Long> countComentarios() {
        return new ResponseEntity<>(comentarioService.count(), HttpStatus.OK);
    }

    // Obtener comentarios por ID de viaje
    @Operation(summary = "Obtener comentarios por viaje", description = "Retorna una lista de comentarios de un viaje específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron comentarios para el viaje")
    })
    @GetMapping("/viaje/{viajeId}")
    public ResponseEntity<Iterable<ComentarioDTO>> getComentariosByViajeId(@PathVariable Long viajeId) {
        return new ResponseEntity<>(comentarioService.findByViajeId(viajeId), HttpStatus.OK);
    }

    // Obtener comentarios por ID de cliente
    @Operation(summary = "Obtener comentarios por cliente", description = "Retorna una lista de comentarios realizados por un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron comentarios del cliente")
    })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Iterable<ComentarioDTO>> getComentariosByClienteId(@PathVariable Long clienteId) {
        return new ResponseEntity<>(comentarioService.findByClienteId(clienteId), HttpStatus.OK);
    }

    // Obtener el promedio de calificaciones de un viaje
    @Operation(summary = "Obtener calificación promedio de un viaje", description = "Retorna el promedio de calificaciones de un viaje específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promedio de calificación obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron calificaciones para el viaje")
    })
    @GetMapping("/viaje/{viajeId}/calificacion-promedio")
    public ResponseEntity<Double> getCalificacionPromedioByViajeId(@PathVariable Long viajeId) {
        return new ResponseEntity<>(comentarioService.getCalificacionPromedioByViajeId(viajeId), HttpStatus.OK);
    }
}
