package com.inventorycontrol.dto.produto;

public record ProdutoResponseDTO(
        Integer id,
        String nome,
        int quantidade,
        Double preco,
        String descricao
) {
}
