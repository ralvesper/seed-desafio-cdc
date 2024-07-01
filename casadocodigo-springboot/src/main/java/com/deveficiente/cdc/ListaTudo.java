package com.deveficiente.cdc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ListaTudo {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/listatudo")
    public ResponseEntity<Map<String, Object>> listaTudo() {

        Map<String, Object> resultado = Map.of(
                "categorias", listaCategorias(),
                "autores", listaAutores()
        );

        return ResponseEntity.ok(resultado);
    }

    private Object listaAutores() {
        return manager.createQuery("select a from Autor a").getResultList();
    }

    private Object listaCategorias() {
        return manager.createQuery("select c from Categoria c").getResultList();
    }
}
