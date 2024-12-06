package com.deveficiente.cdc.cupons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Cupom {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private BigDecimal percentualDesconto;

    @NotNull
    private LocalDate validade;

    public @Positive BigDecimal aplicaDesconto(@Positive BigDecimal total) {
        return total.subtract(total.multiply(percentualDesconto.divide(new BigDecimal(100), RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);
    }

    public boolean valido() {
        return LocalDate.now().compareTo(validade) <= 0;
    }
}
