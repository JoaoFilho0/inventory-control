package com.inventorycontrol.service;

import com.inventorycontrol.dto.produto.ProdutoRequestDTO;
import com.inventorycontrol.model.Produto;
import com.inventorycontrol.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.inventorycontrol.dto.produto.ProdutoResponseDTO;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoResponseDTO add(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setQuantidade(produtoRequestDTO.quantidade());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setDescricao(produtoRequestDTO.descricao());

        Produto salvo = this.produtoRepository.save(produto);
        return toDTO(salvo);
    }

    public ProdutoResponseDTO getById(Integer id) {
        Produto produto = this.produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return toDTO(produto);
    }

    public List<ProdutoResponseDTO> getAll() {
        return this.produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public ProdutoResponseDTO update(Integer id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoAtualizado = this.produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        produtoAtualizado.setNome(produtoRequestDTO.nome());
        produtoAtualizado.setQuantidade(produtoRequestDTO.quantidade());
        produtoAtualizado.setPreco(produtoRequestDTO.preco());
        produtoAtualizado.setDescricao(produtoRequestDTO.descricao());

        Produto atualizado = this.produtoRepository.save(produtoAtualizado);
        return toDTO(atualizado);
    }

    @Transactional
    public void delete(Integer id) {
        this.produtoRepository.deleteById(id);
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
