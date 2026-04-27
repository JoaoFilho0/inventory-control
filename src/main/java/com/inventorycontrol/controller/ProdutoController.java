package com.inventorycontrol.controller;

import com.inventorycontrol.dto.produto.ProdutoRequestDTO;
import com.inventorycontrol.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventorycontrol.dto.produto.ProdutoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> add(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(this.produtoService.add(produtoRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(produtoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAll() {
        return ResponseEntity.ok(produtoService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable(name = "id") Integer id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(this.produtoService.update(id, produtoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        this.produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
