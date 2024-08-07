package com.deveficiente.cdc.paisestado;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    @JsonBackReference
    private @NotNull Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public boolean pertenceAPais(Pais pais) {
        return this.pais.equals(pais);
    }

}
