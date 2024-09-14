package com.deveficiente.cdc.fechamentocompra;

import com.deveficiente.cdc.fechamentocompra.model.Pedido;
import com.deveficiente.cdc.livro.Livro;
import com.deveficiente.cdc.util.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NovoPedido {

   @Positive
   @NotNull
   private BigDecimal total;

   @Size(min = 1)
   @Valid
   private List<NovoPedidoItem> itens = new ArrayList<>();

   public Pedido toModel(EntityManager manager) {

      Assert.isTrue(!itens.isEmpty(), "É necessário ter pelo menos um item no pedido");
      Assert.isTrue(total.compareTo(BigDecimal.ZERO) > 0, "O total do pedido precisa ser maior que zero");
      Assert.isTrue(itens.stream().allMatch(item -> item.getQuantidade() > 0), "A quantidade de todos os itens precisa ser maior que zero");
      Assert.isTrue(total.compareTo(
              itens.stream()
                      .map(item -> {
                         Livro livro = manager.find(Livro.class, item.getIdLivro());
                         return livro.getPreco().multiply(new BigDecimal(item.getQuantidade()));
                      }).reduce(BigDecimal.ZERO, BigDecimal::add)) == 0, "O total do pedido precisa ser igual ao total dos itens");

        Pedido pedido = Pedido.builder()
                .total(total)
                .itens(
                   itens.stream().map(item -> item.toModel(manager)).toList()
                )
                .build();

        pedido.getItens().forEach(item -> item.setPedido(pedido));

        return pedido;
   }
}
