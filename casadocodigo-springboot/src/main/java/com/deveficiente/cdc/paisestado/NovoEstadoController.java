package com.deveficiente.cdc.paisestado;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class NovoEstadoController {

    @Autowired
    private EntityManager manager;

    @PostMapping(value = "/estados")
    @Transactional
    public ResponseEntity<?> novo(@Valid @RequestBody NovoEstadoRequest novoEstado) {
        Estado estado = novoEstado.toModel(manager);
        manager.persist(estado);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estado.getId())
                .toUri();

        return ResponseEntity.created(location).body(estado);
    }
}
