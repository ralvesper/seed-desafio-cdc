package com.deveficiente.cdc.cupons;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.net.URL;

@RestController
public class NovoCupomController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cupons")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoCupomRequest request) {
        Cupom cupom = request.toModel();
        manager.persist(cupom);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cupom.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
