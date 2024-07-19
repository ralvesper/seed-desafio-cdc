package com.deveficiente.cdc.detalhelivro;

import com.deveficiente.cdc.autor.Autor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DetalheSiteAutorResponse {
    private final String nome;
    private final String descricao;

    public DetalheSiteAutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }
}
