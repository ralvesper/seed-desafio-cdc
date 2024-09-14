package com.deveficiente.cdc.fechamentocompra;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NovaCompraResponse {
    private String mensagem;
    private Object data;
}
