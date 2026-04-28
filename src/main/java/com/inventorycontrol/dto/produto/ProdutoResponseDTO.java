package com.inventorycontrol.dto.produto;

import com.inventorycontrol.model.Produto;

public record ProdutoResponseDTO(
        Integer id,
        String nome,
        int quantidade,
        Double preco,
        String descricao
) {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getQuantidade(), produto.getPreco(), produto.getDescricao());
    }
}
