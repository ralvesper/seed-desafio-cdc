package com.deveficiente.cdc.novoautor;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
class NovoAutorControllerTest {

    private NovoAutorController controller;
    private NovoAutorRequest autorRequest;

    @BeforeEach
    void setUp() {
        controller = spy(new NovoAutorController());
        autorRequest = new NovoAutorRequest("Nome", "rodrigo.desenv@gmail.com", "Descrição");
    }

    @Test
    @DisplayName("Should return 201 Created when a new author is created")
    void shouldReturnCreatedWhenNewAuthorIsCreated() {
        // Act
        Uni<Response> response = controller.criar(autorRequest);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.await().indefinitely().getStatus());
        verify(controller, times(1)).criar(autorRequest);
    }

    @Test
    @DisplayName("Should return 400 Bad Request when the author request is invalid")
    void shouldReturnBadRequestWhenAuthorRequestIsInvalid() {
        // Arrange
        NovoAutorRequest invalidRequest = new NovoAutorRequest("", "", "");

        // Act
        Uni<Response> response = controller.criar(invalidRequest);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.await().indefinitely().getStatus());
        verify(controller, times(1)).criar(invalidRequest);
    }

    @Test
    @DisplayName("Should return 409 Conflict when the author already exists")
    void shouldReturnConflictWhenAuthorAlreadyExists() {
        // Arrange
        when(controller.criar(autorRequest)).thenReturn(Uni.createFrom().item(Response.status(Response.Status.CONFLICT).build()));

        // Act
        Uni<Response> response = controller.criar(autorRequest);

        // Assert
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.await().indefinitely().getStatus());
        verify(controller, times(2)).criar(autorRequest);
    }
}