package com.inventorycontrol.controller;

import com.inventorycontrol.dto.produto.ProdutoRequestDTO;
import com.inventorycontrol.dto.produto.ProdutoResponseDTO;
import com.inventorycontrol.model.Produto;
import com.inventorycontrol.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> add(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = produtoService.add(dto);
        return ResponseEntity.ok(toDTO(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Integer id) {
        Produto produto = produtoService.getById(id);
        return ResponseEntity.ok(toDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAll() {
        List<ProdutoResponseDTO> lista = produtoService.getAll()
                .stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Integer id,
                                                     @RequestBody ProdutoRequestDTO dto) {
        Produto produto = produtoService.update(id, dto);
        return ResponseEntity.ok(toDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                produto.getPreco(),
                produto.getDescricao()
        );
    }
}