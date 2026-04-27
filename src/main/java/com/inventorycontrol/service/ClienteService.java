package com.inventorycontrol.service;

import com.inventorycontrol.dto.cliente.ClienteRequestDTO;
import com.inventorycontrol.model.Cliente;
import com.inventorycontrol.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.inventorycontrol.dto.cliente.ClienteResponseDTO;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponseDTO add(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.nome());

        Cliente salvo = this.clienteRepository.save(cliente);
        return toDTO(salvo);
    }

    public ClienteResponseDTO getById(Integer id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return toDTO(cliente);
    }

    public List<ClienteResponseDTO> getAll() {
        return this.clienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public ClienteResponseDTO update(Integer id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteAtualizado = this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        clienteAtualizado.setNome(clienteRequestDTO.nome());

        Cliente atualizado = this.clienteRepository.save(clienteAtualizado);
        return toDTO(atualizado);
    }

    @Transactional
    public void delete(Integer id) {
        this.clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO toDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome()
        );
    }
}
