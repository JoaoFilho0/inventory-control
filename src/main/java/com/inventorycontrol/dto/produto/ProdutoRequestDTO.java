package com.inventorycontrol.dto.produto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequestDTO(
        @NotBlank
        String nome,
        int quantidade,
        double preco,
        @NotBlank
        String descricao
) {
}
