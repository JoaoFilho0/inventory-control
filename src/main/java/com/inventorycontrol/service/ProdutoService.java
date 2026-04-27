package com.inventorycontrol.service;

import com.inventorycontrol.dto.produto.ProdutoRequestDTO;
import com.inventorycontrol.model.Produto;
import com.inventorycontrol.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto add(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setQuantidade(produtoRequestDTO.quantidade());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setDescricao(produtoRequestDTO.descricao());

        return this.produtoRepository.save(produto);
    }

    public Produto getById(Integer id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public List<Produto> getAll() {
        return this.produtoRepository.findAll();
    }

    @Transactional
    public Produto update(Integer id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoAtualizado = this.produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        produtoAtualizado.setNome(produtoRequestDTO.nome());
        produtoAtualizado.setQuantidade(produtoRequestDTO.quantidade());
        produtoAtualizado.setPreco(produtoRequestDTO.preco());
        produtoAtualizado.setDescricao(produtoRequestDTO.descricao());

        return this.produtoRepository.save(produtoAtualizado);
    }

    @Transactional
    public void delete(Integer id) {
        this.produtoRepository.deleteById(id);
    }
}