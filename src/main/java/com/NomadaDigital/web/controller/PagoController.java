package com.NomadaDigital.web.controller;

import com.NomadaDigital.domain.dto.PagoDTO;
import com.NomadaDigital.domain.service.PagoService;
import com.NomadaDigital.persistence.entity.Pago.EstadoPago;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Obtener todos los pagos
    @Operation(summary = "Obtener todos los pagos", description = "Retorna una lista de todos los pagos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<PagoDTO>> getAllPagos() {
        return new ResponseEntity<>(pagoService.findAll(), HttpStatus.OK);
    }

    // Obtener pago por ID
    @Operation(summary = "Obtener pago por ID", description = "Retorna el pago correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar un nuevo pago
    @Operation(summary = "Guardar nuevo pago", description = "Guarda un nuevo pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el pago")
    })
    @PostMapping("/save")
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) {
        return new ResponseEntity<>(pagoService.save(pagoDTO), HttpStatus.CREATED);
    }


    // Actualizar un pago existente
    @Operation(summary = "Actualizar pago", description = "Actualiza los datos de un pago existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el pago"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<PagoDTO> updatePago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        pagoDTO.setId(id); // Asegurar que el ID es el correcto
        return ResponseEntity.ok(pagoService.update(pagoDTO));
    }

    // Eliminar un pago por ID
    @Operation(summary = "Eliminar pago", description = "Elimina el pago correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Contar el número total de pagos
    @Operation(summary = "Contar pagos", description = "Retorna el número total de pagos registrados")
    @ApiResponse(responseCode = "200", description = "Número total de pagos")
    @GetMapping("/count")
    public ResponseEntity<Long> countPagos() {
        return new ResponseEntity<>(pagoService.count(), HttpStatus.OK);
    }

    // Obtener pagos por reserva
    @Operation(summary = "Obtener pagos por reserva", description = "Retorna una lista de pagos asociados a una reserva específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron pagos para la reserva")
    })
    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<Iterable<PagoDTO>> getPagosByReservaId(@PathVariable Long reservaId) {
        return new ResponseEntity<>(pagoService.findByReservaId(reservaId), HttpStatus.OK);
    }

    // Obtener pago por número de transacción
    @Operation(summary = "Obtener pago por número de transacción", description = "Retorna el pago asociado a un número de transacción específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/transaccion/{numeroTransaccion}")
    public ResponseEntity<PagoDTO> getPagoByNumeroTransaccion(@PathVariable String numeroTransaccion) {
        Optional<PagoDTO> pago = pagoService.findByNumeroTransaccion(numeroTransaccion);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener pagos por estado
    @Operation(summary = "Obtener pagos por estado", description = "Retorna una lista de pagos filtrados por estado (Aprobado, Pendiente, Rechazado)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron pagos con ese estado")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Iterable<PagoDTO>> getPagosByEstado(@PathVariable EstadoPago estado) {
        return new ResponseEntity<>(pagoService.findByEstado(estado), HttpStatus.OK);
    }

}
