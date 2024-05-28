package com.deveficiente.cdc.categoria;

import lombok.Data;

@Data
public class NovaCategoriaResponse {
    private Long id;
    private String nome;

    public NovaCategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
