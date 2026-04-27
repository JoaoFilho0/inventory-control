package com.inventorycontrol.service;

import com.inventorycontrol.dto.compra.CompraRequestDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.model.Compra;
import com.inventorycontrol.model.Produto;
import com.inventorycontrol.repository.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.inventorycontrol.repository.ClienteRepository;
import com.inventorycontrol.repository.ProdutoRepository;
import com.inventorycontrol.dto.compra.CompraResponseDTO;

import java.util.List;
import java.util.Objects;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    CompraService(CompraRepository compraRepository,
                  ClienteRepository clienteRepository,
                  ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Compra add(CompraRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        decrementaEstoque(produto);

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setProduto(produto);

        return compraRepository.save(compra);
    }

    public Compra getById(Integer id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
    }

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    @Transactional
    public Compra update(Integer id, CompraRequestDTO dto) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow();

        Produto novoProduto = produtoRepository.findById(dto.produtoId())
                .orElseThrow();

        Produto atual = compra.getProduto();

        if (!Objects.equals(atual.getId(), novoProduto.getId())) {
            atual.setQuantidade(atual.getQuantidade() + 1);
            decrementaEstoque(novoProduto);
        }

        compra.setCliente(cliente);
        compra.setProduto(novoProduto);

        return compraRepository.save(compra);
    }

    @Transactional
    public void delete(Integer id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));

        Produto produto = compra.getProduto();
        produto.setQuantidade(produto.getQuantidade() + 1);

        compraRepository.delete(compra);
    }

    private void decrementaEstoque(Produto produto) {
        if (produto.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade insuficiente em estoque.");
        }

        produto.setQuantidade(produto.getQuantidade() - 1);
    }
}