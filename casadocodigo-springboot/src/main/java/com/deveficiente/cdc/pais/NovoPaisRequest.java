package com.deveficiente.cdc.pais;

import com.deveficiente.cdc.categoria.Categoria;
import com.deveficiente.cdc.util.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NovoPaisRequest {
    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;
}
