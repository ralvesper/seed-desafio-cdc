package com.deveficiente.cdc.compra.dto;

import com.deveficiente.cdc.compra.model.ItemPedido;
import com.deveficiente.cdc.livro.Livro;
import com.deveficiente.cdc.util.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NovoPedidoItem {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @Positive
    @NotNull
    private int quantidade;

    public ItemPedido toModel(EntityManager manager) {
        Livro livro = manager.find(Livro.class, idLivro);
        return ItemPedido.builder()
                .livro(livro)
                .quantidade(quantidade)
                .build();
    }
}