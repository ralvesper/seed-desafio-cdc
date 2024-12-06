package com.deveficiente.cdc.compra.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NovaCompraResponse {
    private String mensagem;
    private DadosCompraResponse data;
}
