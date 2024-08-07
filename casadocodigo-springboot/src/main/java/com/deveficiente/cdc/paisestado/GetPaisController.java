package com.deveficiente.cdc.paisestado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class GetPaisController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/paises")
    public ResponseEntity<?> lista() {
        return ResponseEntity.ok(manager.createQuery("select p from Pais p").getResultList());
    }

    @GetMapping(value = "/paises/{id}")
    public ResponseEntity<Pais> listaEstadosPorPais(@Valid @NotNull @PathVariable("id") Long id) {
        Pais pais = manager.find(Pais.class, id);
        return ResponseEntity.ok(pais);
    }
}
