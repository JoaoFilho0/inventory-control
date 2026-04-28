package com.inventorycontrol.dto.cliente;

import com.inventorycontrol.model.Cliente;

public record ClienteResponseDTO(
        Integer id,
        String nome
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome());
    }
}