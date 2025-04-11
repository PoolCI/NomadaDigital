package com.NomadaDigital.web.controller;

import com.NomadaDigital.domain.dto.ReservaDTO;
import com.NomadaDigital.domain.service.ReservaService;
import com.NomadaDigital.persistence.entity.Reserva.EstadoReserva;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Obtener todas las reservas
    @Operation(summary = "Obtener todas las reservas", description = "Retorna una lista de todas las reservas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ReservaDTO>> getAllReservas() {
        return new ResponseEntity<>(reservaService.findAll(), HttpStatus.OK);
    }

    // Obtener reserva por ID
    @Operation(summary = "Obtener reserva por ID", description = "Retorna la reserva correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservaService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar una nueva reserva
    @Operation(summary = "Guardar nueva reserva", description = "Guarda una nueva reserva en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la reserva")
    })
    @PostMapping("/save")
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        return new ResponseEntity<>(reservaService.save(reservaDTO), HttpStatus.CREATED);
    }

    // Actualizar una reserva existente
    @Operation(summary = "Actualizar reserva", description = "Actualiza los datos de una reserva existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar la reserva"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        reservaDTO.setId(id); // Asegurar que el ID es el correcto
        return ResponseEntity.ok(reservaService.update(reservaDTO));
    }

    // Eliminar una reserva por ID
    @Operation(summary = "Eliminar reserva", description = "Elimina la reserva correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Contar el número total de reservas
    @Operation(summary = "Contar reservas", description = "Retorna el número total de reservas registradas")
    @ApiResponse(responseCode = "200", description = "Número total de reservas")
    @GetMapping("/count")
    public ResponseEntity<Long> countReservas() {
        return new ResponseEntity<>(reservaService.count(), HttpStatus.OK);
    }

    // Obtener reservas por cliente
    @Operation(summary = "Obtener reservas por cliente", description = "Retorna una lista de reservas realizadas por un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas para el cliente")
    })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Iterable<ReservaDTO>> getReservasByClienteId(@PathVariable Long clienteId) {
        return new ResponseEntity<>(reservaService.findByClienteId(clienteId), HttpStatus.OK);
    }

    // Obtener reservas por viaje
    @Operation(summary = "Obtener reservas por viaje", description = "Retorna una lista de reservas asociadas a un viaje específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas para el viaje")
    })
    @GetMapping("/viaje/{viajeId}")
    public ResponseEntity<Iterable<ReservaDTO>> getReservasByViajeId(@PathVariable Long viajeId) {
        return new ResponseEntity<>(reservaService.findByViajeId(viajeId), HttpStatus.OK);
    }

    // Obtener reservas por estado
    @Operation(summary = "Obtener reservas por estado", description = "Retorna una lista de reservas filtradas por estado (Confirmada, Cancelada, Pendiente)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron reservas con ese estado")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Iterable<ReservaDTO>> getReservasByEstado(@PathVariable EstadoReserva estado) {
        return new ResponseEntity<>(reservaService.findByEstado(estado), HttpStatus.OK);
    }
}
