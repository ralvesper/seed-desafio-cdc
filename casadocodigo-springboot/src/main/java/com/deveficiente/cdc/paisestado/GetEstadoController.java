package com.deveficiente.cdc.paisestado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetEstadoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/estados")
    public ResponseEntity<?> lista() {
        return ResponseEntity.ok(manager.createQuery("select p from Estado p").getResultList());
    }
}
