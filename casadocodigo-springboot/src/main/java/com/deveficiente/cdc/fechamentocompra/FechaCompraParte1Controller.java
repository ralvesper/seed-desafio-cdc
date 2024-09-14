package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.fechamentocompra.model.Compra;
import com.deveficiente.cdc.fechamentocompra.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class FechaCompraParte1Controller {

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerificaDocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator);
    }

    @PostMapping(value = "/compras")
    @Transactional
    public ResponseEntity<?> fechaCompra(@RequestBody @Valid NovaCompraRequest request) {

        Compra novaCompra = request.toModel(manager);

        manager.persist(novaCompra.getPedido());
        Pedido pedido = manager.find(Pedido.class, novaCompra.getPedido().getId());
        novaCompra.setPedido(pedido);

        manager.persist(novaCompra);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaCompra.getId())
                .toUri();

        NovaCompraResponse res = NovaCompraResponse.builder()
                .mensagem("Compra realizada com sucesso")
                .data(novaCompra).build();

        return ResponseEntity.created(location).body(res);
    }


}
