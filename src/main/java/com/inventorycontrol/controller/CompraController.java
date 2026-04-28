package com.inventorycontrol.controller;

import com.inventorycontrol.dto.compra.CompraRequestDTO;
import com.inventorycontrol.dto.compra.CompraResponseDTO;
import com.inventorycontrol.model.Compra;
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

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private final CompraService compraService;

    CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<CompraResponseDTO> add(@RequestBody CompraRequestDTO dto) {
        Compra compra = compraService.add(dto);
        return ResponseEntity.ok(new CompraResponseDTO(compra));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> getById(@PathVariable Integer id) {
        Compra compra = compraService.getById(id);
        return ResponseEntity.ok(new CompraResponseDTO(compra));
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> getAll() {
        List<CompraResponseDTO> lista = compraService.getAll()
                .stream()
                .map(CompraResponseDTO::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> update(@PathVariable Integer id,
                                                    @RequestBody CompraRequestDTO dto) {
        Compra compra = compraService.update(id, dto);
        return ResponseEntity.ok(new CompraResponseDTO(compra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        compraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}