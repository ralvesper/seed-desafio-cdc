package com.deveficiente.cdc.compra.dto;

import com.deveficiente.cdc.cupons.CupomAplicado;
import com.deveficiente.cdc.compra.model.Compra;
import com.deveficiente.cdc.compra.model.Pedido;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DadosCompraResponse {

    private Long id;
    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private String telefone;
    private String cep;
    private Pais pais;
    private Estado estado;
    private Pedido pedido;
    private CupomAplicado cupomAplicado;

    public static DadosCompraResponse of(Compra compra) {

        if (compra.getCupomAplicado() != null) {
            compra.getPedido().aplicaCupom(compra.getCupomAplicado().getCupom());
        }

        return DadosCompraResponse.builder()
                .id(compra.getId())
                .email(compra.getEmail())
                .nome(compra.getNome())
                .sobrenome(compra.getSobrenome())
                .documento(compra.getDocumento())
                .endereco(compra.getEndereco())
                .complemento(compra.getComplemento())
                .cidade(compra.getCidade())
                .telefone(compra.getTelefone())
                .cep(compra.getCep())
                .pais(compra.getPais())
                .estado(compra.getEstado())
                .pedido(compra.getPedido())
                .cupomAplicado(compra.getCupomAplicado())
                .build();
    }

}
