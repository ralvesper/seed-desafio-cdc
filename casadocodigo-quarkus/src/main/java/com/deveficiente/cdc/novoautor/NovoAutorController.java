package com.deveficiente.cdc.novoautor;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@ApplicationScoped
@Path("/autores")
public class NovoAutorController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<Response> criar(@Valid NovoAutorRequest autor) {
        System.out.println("Criando um novo autor");

        Uni<Autor> autorExistente = Autor.find("email", autor.getEmail()).firstResult();

        return autorExistente
            .onItem().transformToUni(autorEncontrado -> {
                if (autorEncontrado != null) {
                    return Uni.createFrom().failure(new IllegalArgumentException("Email já cadastrado!"));
                }
                return autor.toModel().persist();
            })
            .map(autorSalvo -> {
                URI uri = UriBuilder.fromResource(NovoAutorController.class).path("/{id}").build(0);
                return Response.created(uri).build();
            }).onFailure().recoverWithItem(erro -> Response.status(Response.Status.CONFLICT).entity(erro.getMessage()).build());
    }
}
