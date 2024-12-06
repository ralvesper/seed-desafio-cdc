package com.deveficiente.cdc.cupons;

import com.deveficiente.cdc.util.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NovoCupomRequest {

    @NotBlank
    @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
    private String codigo;

    @Positive
    @NotNull
    private BigDecimal percentualDesconto;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;


    public Cupom toModel() {
        Cupom cupom = new Cupom();
        cupom.setCodigo(this.codigo);
        cupom.setPercentualDesconto(this.percentualDesconto);
        cupom.setValidade(this.validade);
        return cupom;
    }
}
