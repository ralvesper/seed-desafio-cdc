package com.deveficiente.cdc.cupons;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetCupomController {

    private final CupomRepository cupomRepository;

    public GetCupomController(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @GetMapping("/cupons/{codigo}")
    public ResponseEntity<GetCupomResponse> getCupons(@PathVariable String codigo) {

        Optional<Cupom> cupom = cupomRepository.findByCodigo(codigo);

        if (cupom.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new GetCupomResponse(cupom.get()));
    }

}
