package com.deveficiente.cdc.pais;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
public class NovoPaisController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/paises")
    @Transactional
    public ResponseEntity<?> novo(@RequestBody @Validated NovoPaisRequest novoPais) {

       Pais pais = Pais.builder().nome(novoPais.getNome()).build();

       manager.persist(pais);

       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pais.getId())
                .toUri();

       return ResponseEntity.created(location).build();
    }
}
