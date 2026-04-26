package com.inventorycontrol.dto.produto;

public record ProdutoRequestDTO(
        String nome,
        int quantidade,
        double preco,
        String descricao
) {
}
