package com.upc.pruebas.infraestructure.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pruebas.PruebasunitariasApplication;
import com.upc.pruebas.application.dto.ClienteRequestDTO;
import com.upc.pruebas.domain.model.Cliente;
import com.upc.pruebas.infraestructure.persistence.repository.ClienteRepositoryImpl;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PruebasunitariasApplication.class)
@AutoConfigureMockMvc
@Transactional  // Cada test se ejecuta en una transacción
@Rollback        // Y se revierte al finalizar el test
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.MethodName.class)
// Ya no necesitas @DirtiesContext en este caso
class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteRepositoryImpl clienteRepositoryImpl;


    @Test
    void crearCliente_deberiaRetornar201YClientePersistido() throws Exception {
        ClienteRequestDTO requestDTO = new ClienteRequestDTO("Juan Pérez", "juan@example.com");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    void obtenerTodosLosClientes_deberiaRetornar200YListaDeClientes() throws Exception {

            ClienteRequestDTO cliente1 = new ClienteRequestDTO("Carlos Ruiz", "carlos@example.com");
            ClienteRequestDTO cliente2 = new ClienteRequestDTO("Lucía Gómez", "lucia@example.com");

            // Realizamos los POST
            mockMvc.perform(post("/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(cliente1)))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(cliente2)))
                    .andExpect(status().isCreated());

            // Verificar que los clientes fueron guardados en la base de datos
            List<Cliente> clientesGuardados = clienteRepositoryImpl.listar();
            System.out.println("Clientes guardados en la base de datos: " + clientesGuardados.size());
            Assertions.assertThat(clientesGuardados.size()).isGreaterThan(1);

            // Act & Assert: Realizamos el GET y comprobamos la respuesta
            mockMvc.perform(get("/clientes"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].nombre").value("Carlos Ruiz"))
                    .andExpect(jsonPath("$[1].nombre").value("Lucía Gómez"));
        }

}
