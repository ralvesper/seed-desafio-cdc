package com.deveficiente.cdc.livro;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class LivrosResponse {
    private List<NovoLivroDTO> livros;
}
