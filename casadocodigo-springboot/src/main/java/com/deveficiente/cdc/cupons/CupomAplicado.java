package com.deveficiente.cdc.cupons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@Data
public class CupomAplicado {

    private final @Positive @NotNull BigDecimal percentualDescontoMomento;
    private final @Future @NotNull LocalDate validateDescontoMomento;

    @ManyToOne
    @JsonIgnore
    private Cupom cupom;

    @Deprecated
    public CupomAplicado() {
        this.percentualDescontoMomento = BigDecimal.ZERO;
        this.validateDescontoMomento = LocalDate.now();
    }

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validateDescontoMomento = cupom.getValidade();
        
    }

}
