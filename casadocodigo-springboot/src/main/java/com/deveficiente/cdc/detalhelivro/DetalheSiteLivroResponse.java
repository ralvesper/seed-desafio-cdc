package com.deveficiente.cdc.detalhelivro;

import com.deveficiente.cdc.livro.Livro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@ToString
public class DetalheSiteLivroResponse {

    private final String titulo;
    private final String resumo;
    private final String sumario;
    private final BigDecimal preco;
    private final int numeroDePaginas;
    private final String isbn;
    @JsonIgnore
    private final LocalDate dataPublicacao;
    private final String categoria;
    private final DetalheSiteAutorResponse autor;

    public DetalheSiteLivroResponse(Livro livro) {
      this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.autor = new DetalheSiteAutorResponse(livro.getAutor());
        this.categoria = livro.getCategoria().getNome();
    }

    @JsonProperty("dataPublicacao")
    public String getDataPublicacaoFormatada() {
      return this.dataPublicacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
