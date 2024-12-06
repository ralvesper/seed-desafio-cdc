package com.deveficiente.cdc.compra.controller;

import com.deveficiente.cdc.compra.dto.DadosCompraResponse;
import com.deveficiente.cdc.compra.model.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DadosCompraController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/compras/{id}")
    public ResponseEntity<DadosCompraResponse> dadosCompra(@PathVariable Long id) {

        Compra compra = manager.find(Compra.class, id);
        if (compra == null) {
            return ResponseEntity.notFound().build();
        }

        if (compra.getCupomAplicado() != null) {
           compra.getPedido().aplicaCupom(compra.getCupomAplicado().getCupom());
        }

        return ResponseEntity.ok(DadosCompraResponse.of(compra));

    }

}
