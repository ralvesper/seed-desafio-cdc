package com.deveficiente.cdc.categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ProibeCategoriaDuplicadaValidator proibeCategoriaDuplicadaValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeCategoriaDuplicadaValidator);
    }

    @PostMapping("/categorias")
    @Transactional
    public ResponseEntity<NovaCategoriaResponse> cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel();
        manager.persist(categoria);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(new NovaCategoriaResponse(categoria));
    }

}
