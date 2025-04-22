package com.upc.pruebas.application.service;

import com.upc.pruebas.application.dto.ClienteRequestDTO;
import com.upc.pruebas.application.dto.ClienteResponseDTO;
import com.upc.pruebas.domain.exception.EMailInvalidoException;
import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteApplicationService {

    private final ClienteRepository clienteRepository;

    public ClienteApplicationService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO crearCliente(ClienteRequestDTO dto) {
        try {
            Cliente cliente = new Cliente(dto.getNombre(), dto.getEmail());
            cliente = clienteRepository.insertar(cliente);
            return new ClienteResponseDTO(cliente.getId(), cliente.getNombre(), cliente.getEmail());
        } catch (EMailInvalidoException e) {
            // Aquí puedes manejar el error de manera específica para la capa de aplicación
            throw new RuntimeException("Error al crear el cliente: " + e.getMessage(), e);
        }
    }

    public List<ClienteResponseDTO> obtenerTodos() {
        return clienteRepository.listar().stream()
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNombre(), c.getEmail()))
                .collect(Collectors.toList());
    }


}
