package com.deveficiente.cdc.livro;


import com.deveficiente.cdc.autor.Autor;
import com.deveficiente.cdc.categoria.Categoria;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    private @NotBlank String resumo;
    private @NotBlank String sumario;
    private @NotNull @Min(20) BigDecimal preco;
    private @NotNull @Min(100) int numeroDePaginas;
    private @NotBlank String isbn;
    private @NotNull @Future LocalDate dataPublicacao;

    @ManyToOne
    private @NotNull @Valid Autor autor;

    @ManyToOne
    private @NotNull @Valid Categoria categoria;
}
