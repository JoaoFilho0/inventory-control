package com.inventorycontrol.dto.compra;

import jakarta.validation.constraints.NotNull;

public record CompraRequestDTO(
        @NotNull
        Integer clienteId,
        @NotNull
        Integer produtoId
) {
}
