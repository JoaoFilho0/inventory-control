package com.inventorycontrol.dto.compra;

public record CompraResponseDTO(
        Integer id,
        Integer clienteId,
        Integer produtoId
) {}