package com.deveficiente.cdc.paisestado;

import com.deveficiente.cdc.util.ExistsId;
import com.deveficiente.cdc.util.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private final String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private final Long idPais;

    public NovoEstadoRequest(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, idPais);
        return new Estado(nome, pais);
    }
}
