package com.deveficiente.cdc.compra.model;


import com.deveficiente.cdc.cupons.Cupom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pedido {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Positive
    private BigDecimal total;

    @Transient
    private BigDecimal valorFinal;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public void aplicaCupom(Cupom cupom) {
        this.valorFinal = cupom.aplicaDesconto(total);
    }

    public BigDecimal getValorFinal() {
        return valorFinal == null ? total : valorFinal;
    }
}
