package com.inventorycontrol.service;

import com.inventorycontrol.dto.cliente.ClienteRequestDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente add(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.nome());

        return this.clienteRepository.save(cliente);
    }

    public Cliente getById(Integer id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public List<Cliente> getAll() {
        return this.clienteRepository.findAll();
    }

    @Transactional
    public Cliente update(Integer id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteAtualizado = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        clienteAtualizado.setNome(clienteRequestDTO.nome());

        return this.clienteRepository.save(clienteAtualizado);
    }

    @Transactional
    public void delete(Integer id) {
        this.clienteRepository.deleteById(id);
    }
}