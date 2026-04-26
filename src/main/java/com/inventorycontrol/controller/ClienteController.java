package com.inventorycontrol.controller;

import com.inventorycontrol.dto.cliente.ClienteRequestDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> add(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(this.clienteService.add(clienteRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable(name = "id") Integer id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(this.clienteService.update(id, clienteRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
