package com.deveficiente.cdc.cupons;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCupomResponse {

    private String codigo;
    private double percentualDesconto;
    private String validade;

    public GetCupomResponse(Cupom cupom) {
        this.codigo = cupom.getCodigo();
        this.percentualDesconto = cupom.getPercentualDesconto().doubleValue();
        this.validade = cupom.getValidade().toString();
    }
}
