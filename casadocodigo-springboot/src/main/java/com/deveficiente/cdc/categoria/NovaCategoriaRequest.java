package com.deveficiente.cdc.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovaCategoriaRequest {

    @NotBlank
    private String nome;

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
