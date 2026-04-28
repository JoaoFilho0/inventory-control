package com.inventorycontrol.dto.compra;

import com.inventorycontrol.dto.cliente.ClienteResponseDTO;
import com.inventorycontrol.dto.produto.ProdutoResponseDTO;
import com.inventorycontrol.model.Compra;

public record CompraResponseDTO(
        Integer id,
        ClienteResponseDTO clienteResponse,
        ProdutoResponseDTO produtoResponse
) {
    public CompraResponseDTO(Compra compra) {
        this(compra.getId(),
                new ClienteResponseDTO(compra.getCliente()),
                new ProdutoResponseDTO(compra.getProduto()));
    }
}