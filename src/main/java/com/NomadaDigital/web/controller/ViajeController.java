package com.NomadaDigital.web.controller;

import com.NomadaDigital.domain.dto.ViajeDTO;
import com.NomadaDigital.domain.service.ViajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    // Obtener todos los viajes
    @Operation(summary = "Obtener todos los viajes", description = "Retorna una lista de todos los viajes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ViajeDTO>> getAllViajes() {
        return new ResponseEntity<>(viajeService.findAll(), HttpStatus.OK);
    }

    // Obtener viaje por ID*
    @Operation(summary = "Obtener viaje por ID", description = "Retorna el viaje correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(viajeService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar un nuevo viaje
    @Operation(summary = "Guardar nuevo viaje", description = "Guarda un nuevo viaje en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viaje creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el viaje")
    })
    @PostMapping("/save")
    public ResponseEntity<ViajeDTO> createViaje(@RequestBody ViajeDTO viajeDTO) {
        return new ResponseEntity<>(viajeService.save(viajeDTO), HttpStatus.CREATED);
    }

    // Actualizar un viaje existente
    @Operation(summary = "Actualizar viaje", description = "Actualiza los datos de un viaje existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el viaje"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ViajeDTO> updateViaje(@PathVariable Long id, @RequestBody ViajeDTO viajeDTO) {
        viajeDTO.setId(id); // Asegurar que el ID es el correcto
        return ResponseEntity.ok(viajeService.update(viajeDTO));
    }

    // Eliminar un viaje por ID
    @Operation(summary = "Eliminar viaje", description = "Elimina el viaje correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteViaje(@PathVariable Long id) {
        viajeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Contar el número total de viajes
    @Operation(summary = "Contar viajes", description = "Retorna el número total de viajes registrados")
    @ApiResponse(responseCode = "200", description = "Número total de viajes")
    @GetMapping("/count")
    public ResponseEntity<Long> countViajes() {
        return new ResponseEntity<>(viajeService.count(), HttpStatus.OK);
    }

    // Obtener viajes por destino
    @Operation(summary = "Obtener viajes por destino", description = "Retorna una lista de viajes que tienen un destino específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viajes obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron viajes con ese destino")
    })
    @GetMapping("/destino/{destino}")
    public ResponseEntity<Iterable<ViajeDTO>> getViajesByDestino(@PathVariable String destino) {
        return new ResponseEntity<>(viajeService.findByDestino(destino), HttpStatus.OK);
    }

    // Obtener viajes dentro de un rango de fechas
    @Operation(summary = "Obtener viajes por rango de fechas", description = "Retorna una lista de viajes dentro de un rango de fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viajes obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron viajes en ese rango de fechas")
    })
    @GetMapping("/fechas")
    public ResponseEntity<Iterable<ViajeDTO>> getViajesByFechas(
            @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        return new ResponseEntity<>(viajeService.findByFechaInicioBetween(fechaInicio, fechaFin), HttpStatus.OK);
    }

    // Obtener solo los viajes activos
    @Operation(summary = "Obtener viajes activos", description = "Retorna una lista de viajes que están activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viajes activos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron viajes activos")
    })
    @GetMapping("/activos")
    public ResponseEntity<Iterable<ViajeDTO>> getViajesActivos() {
        return new ResponseEntity<>(viajeService.findByActivoTrue(), HttpStatus.OK);
    }

    // Obtener viaje por ID*
    @Operation(summary = "Obtener viaje por su codigoUnico", description = "Retorna el viaje correspondiente al CodigoUnico proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    // Obtener un viaje por su codigo Unico
    @GetMapping("/codigoUnico/{codigoUnico}")
    public ResponseEntity<ViajeDTO> getViajeByCodigoUnico(@PathVariable String codigoUnico) {
        try {
            return ResponseEntity.ok(viajeService.findByCodigoUnico(codigoUnico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
