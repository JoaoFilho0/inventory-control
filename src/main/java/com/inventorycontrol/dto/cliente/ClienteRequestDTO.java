package com.inventorycontrol.dto.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank
        String nome
) {
}
