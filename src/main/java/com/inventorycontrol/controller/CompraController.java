package com.inventorycontrol.controller;

import com.inventorycontrol.dto.compra.CompraRequestDTO;
import com.inventorycontrol.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventorycontrol.dto.compra.CompraResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<CompraResponseDTO> add(@RequestBody CompraRequestDTO compraRequestDTO) {
        return ResponseEntity.ok(this.compraService.add(compraRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(compraService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> getAll() {
        return ResponseEntity.ok(compraService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> update(@PathVariable(name = "id") Integer id, @RequestBody CompraRequestDTO compraRequestDTO) {
        return ResponseEntity.ok(this.compraService.update(id, compraRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        this.compraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
