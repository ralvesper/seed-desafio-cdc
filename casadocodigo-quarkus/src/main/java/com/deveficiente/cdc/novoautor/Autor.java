package com.deveficiente.cdc.novoautor;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import io.smallrye.common.constraint.Assert;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Autor extends PanacheEntityBase {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;

    @Column(unique = true)
    public String email;

    public String descricao;
    public LocalDateTime dataCriacao = LocalDateTime.now();

    public Autor(Long id, String nome, String email, String descricao) {

        Assert.checkNotEmptyParam(nome, "Nome é obrigatório");
        Assert.checkNotEmptyParam(email, "Email é obrigatório");
        Assert.checkNotEmptyParam(descricao, "Descrição é obrigatória");
        Assert.checkMaximumParameter(descricao, 400, descricao.length());

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor() {}
}
