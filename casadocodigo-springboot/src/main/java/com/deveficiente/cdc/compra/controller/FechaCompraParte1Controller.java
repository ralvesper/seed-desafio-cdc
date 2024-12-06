package com.deveficiente.cdc.compra.controller;

import com.deveficiente.cdc.compra.dto.DadosCompraResponse;
import com.deveficiente.cdc.compra.dto.NovaCompraRequest;
import com.deveficiente.cdc.compra.dto.NovaCompraResponse;
import com.deveficiente.cdc.compra.validator.EstadoPertenceAPaisValidator;
import com.deveficiente.cdc.compra.validator.VerificaDocumentoCpfCnpjValidator;
import com.deveficiente.cdc.cupons.CupomRepository;
import com.deveficiente.cdc.compra.model.Compra;
import com.deveficiente.cdc.compra.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.slf4j.Logger;
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

    private final Logger log = org.slf4j.LoggerFactory.getLogger(FechaCompraParte1Controller.class);

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @Autowired
    private CupomRepository cupomRepository;

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerificaDocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator);
    }

    @PostMapping(value = "/compras")
    @Transactional
    public ResponseEntity<?> fechaCompra(@RequestBody @Valid NovaCompraRequest request) {

        Compra novaCompra = request.toModel(manager, cupomRepository);

        manager.persist(novaCompra.getPedido());
        Pedido pedido = manager.find(Pedido.class, novaCompra.getPedido().getId());
        novaCompra.setPedido(pedido);

        manager.persist(novaCompra);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaCompra.getId())
                .toUri();


        DadosCompraResponse data =  DadosCompraResponse.of(novaCompra);
        log.info("Compra realizada com sucesso: {}", data);

        NovaCompraResponse res = NovaCompraResponse.builder()
                .mensagem("Compra realizada com sucesso")
                .data(data).build();

        return ResponseEntity.created(location).body(res);
    }


}
