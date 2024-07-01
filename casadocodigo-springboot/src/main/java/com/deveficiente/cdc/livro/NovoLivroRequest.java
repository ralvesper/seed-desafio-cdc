package com.deveficiente.cdc.livro;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoLivroRequest {
    private @NotNull @Valid NovoLivroDTO livro;
}
