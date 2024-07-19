package com.deveficiente.cdc.livro;

import com.deveficiente.cdc.autor.Autor;
import com.deveficiente.cdc.categoria.Categoria;
import com.deveficiente.cdc.util.ExistsId;
import com.deveficiente.cdc.util.UniqueValue;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoLivroDTO {

    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal preco;
    @Min(100)
    private int numeroDePaginas;
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;


    public Livro toModel(Function<Long, Categoria> categoriaFinder, Function<Long, Autor> autorFinder) {
        return new Livro(null, this.titulo, this.resumo, this.sumario, this.preco, this.numeroDePaginas, this.isbn, this.dataPublicacao,
                autorFinder.apply(this.idAutor), categoriaFinder.apply(this.idCategoria));
    }

    public NovoLivroDTO fromModel(Livro livro) {
        return new NovoLivroDTO(livro.getId(), livro.getTitulo(), livro.getResumo(), livro.getSumario(), livro.getPreco(), livro.getNumeroDePaginas(), livro.getIsbn(), livro.getDataPublicacao(), livro.getCategoria().getId(), livro.getAutor().getId());
    }
}
