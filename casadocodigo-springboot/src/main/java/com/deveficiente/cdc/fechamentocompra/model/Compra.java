package com.deveficiente.cdc.fechamentocompra.model;

import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Compra {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @OneToOne
    private Pedido pedido;

}
