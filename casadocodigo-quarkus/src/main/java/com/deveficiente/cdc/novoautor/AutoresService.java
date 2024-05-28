package com.deveficiente.cdc.novoautor;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AutoresService {

    @Inject
    Vertx vertx;

    public Uni<Boolean> autorJaExiste(String email) {
        return Autor.find("email", email).firstResult()
                .map(autor -> autor != null);
    }

}
