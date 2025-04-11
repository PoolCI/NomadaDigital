package com.NomadaDigital.web.controller;

import com.NomadaDigital.domain.dto.ClienteDTO;
import com.NomadaDigital.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ClienteDTO>> getAllClientes() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    // Obtener cliente por ID
    @Operation(summary = "Obtener cliente por ID", description = "Retorna el cliente correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clienteService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Guardar un nuevo cliente
    @Operation(summary = "Guardar nuevo cliente", description = "Guarda un nuevo cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el cliente")
    })
    @PostMapping("/save")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.save(clienteDTO), HttpStatus.CREATED);
    }

    // Actualizar un cliente existente
    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(id); // Asegurar que el ID es el correcto
        return ResponseEntity.ok(clienteService.update(clienteDTO));
    }

    // Eliminar un cliente por ID
    @Operation(summary = "Eliminar cliente", description = "Elimina el cliente correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Contar el número total de clientes
    @Operation(summary = "Contar clientes", description = "Retorna el número total de clientes registrados")
    @ApiResponse(responseCode = "200", description = "Número total de clientes")
    @GetMapping("/count")
    public ResponseEntity<Long> countClientes() {
        return new ResponseEntity<>(clienteService.count(), HttpStatus.OK);
    }

    // Buscar cliente por email
    @Operation(summary = "Buscar cliente por email", description = "Retorna un cliente si su email está registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteDTO> getClienteByEmail(@PathVariable String email) {
        Optional<ClienteDTO> cliente = clienteService.findByEmail(email);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
