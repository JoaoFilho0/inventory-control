package com.inventorycontrol.controller;

import com.inventorycontrol.dto.cliente.ClienteRequestDTO;
import com.inventorycontrol.dto.cliente.ClienteResponseDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> add(@RequestBody ClienteRequestDTO dto) {
        Cliente cliente = clienteService.add(dto);
        return ResponseEntity.ok(toDTO(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Integer id) {
        Cliente cliente = clienteService.getById(id);
        return ResponseEntity.ok(toDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAll() {
        List<ClienteResponseDTO> lista = clienteService.getAll()
                .stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Integer id,
                                                     @RequestBody ClienteRequestDTO dto) {
        Cliente cliente = clienteService.update(id, dto);
        return ResponseEntity.ok(toDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔥 Conversão no controller
    private ClienteResponseDTO toDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome()
        );
    }
}