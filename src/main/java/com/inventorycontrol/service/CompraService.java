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
    public CompraResponseDTO add(CompraRequestDTO compraRequestDTO) {
        Cliente cliente = clienteRepository.findById(compraRequestDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Produto produto = produtoRepository.findById(compraRequestDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        decrementaEstoque(produto);

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setProduto(produto);

        Compra salva = this.compraRepository.save(compra);
        return toDTO(salva);
    }

    public CompraResponseDTO getById(Integer id) {
        Compra compra = this.compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));

        return toDTO(compra);
    }

    public List<CompraResponseDTO> getAll() {
        return this.compraRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public CompraResponseDTO update(Integer id, CompraRequestDTO compraRequestDTO) {
        Compra compraAtualizada = this.compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
        Cliente cliente = clienteRepository.findById(compraRequestDTO.clienteId())
                .orElseThrow();

        Produto produtoNovo = produtoRepository.findById(compraRequestDTO.produtoId())
                .orElseThrow();
        Produto produtoAtual = compraAtualizada.getProduto();

        if (!Objects.equals(produtoAtual.getId(), produtoNovo.getId())) {
            produtoAtual.setQuantidade(produtoAtual.getQuantidade() + 1);
            decrementaEstoque(produtoNovo);
        }

        compraAtualizada.setCliente(cliente);
        compraAtualizada.setProduto(produtoNovo);

        Compra atualizada = this.compraRepository.save(compraAtualizada);
        return toDTO(atualizada);
    }

    @Transactional
    public void delete(Integer id) {
        Compra compra = this.compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada."));
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

    private CompraResponseDTO toDTO(Compra compra) {
        return new CompraResponseDTO(
                compra.getId(),
                compra.getCliente().getId(),
                compra.getProduto().getId()
        );
    }
}
