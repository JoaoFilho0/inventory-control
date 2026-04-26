package com.inventorycontrol.service;

import com.inventorycontrol.dto.compra.CompraRequestDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.model.Compra;
import com.inventorycontrol.model.Produto;
import com.inventorycontrol.repository.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    CompraService(CompraRepository compraRepository,
                  ClienteService clienteService,
                  ProdutoService produtoService) {
        this.compraRepository = compraRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @Transactional
    public Compra add(CompraRequestDTO compraRequestDTO) {
        Cliente cliente = clienteService.getById(compraRequestDTO.clienteId());
        Produto produto = produtoService.getById(compraRequestDTO.produtoId());
        decrementaEstoque(produto);

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setProduto(produto);

        return this.compraRepository.save(compra);
    }

    public Compra getById(Integer id) {
        return this.compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada."));
    }

    public List<Compra> getAll() {
        return this.compraRepository.findAll();
    }

    @Transactional
    public Compra update(Integer id, CompraRequestDTO compraRequestDTO) {
        Compra compraAtualizada = this.getById(id);
        Cliente cliente = clienteService.getById(compraRequestDTO.clienteId());
        Produto produtoNovo = produtoService.getById(compraRequestDTO.produtoId());
        Produto produtoAtual = compraAtualizada.getProduto();

        if (!Objects.equals(produtoAtual.getId(), produtoNovo.getId())) {
            produtoAtual.setQuantidade(produtoAtual.getQuantidade() + 1);
            decrementaEstoque(produtoNovo);
        }

        compraAtualizada.setCliente(cliente);
        compraAtualizada.setProduto(produtoNovo);

        return this.compraRepository.save(compraAtualizada);
    }

    @Transactional
    public void delete(Integer id) {
        Compra compra = this.getById(id);
        Produto produto = compra.getProduto();
        produto.setQuantidade(produto.getQuantidade() + 1);

        this.compraRepository.delete(compra);
    }

    private void decrementaEstoque(Produto produto) {
        if (produto.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade insuficiente em estoque para o produto informado.");
        }

        produto.setQuantidade(produto.getQuantidade() - 1);
    }
}
