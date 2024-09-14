package com.deveficiente.cdc.fechamentocompra;


import com.deveficiente.cdc.fechamentocompra.model.Compra;
import com.deveficiente.cdc.fechamentocompra.model.Pedido;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import com.deveficiente.cdc.util.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NovaCompraRequest {

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
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    @Valid
    @NotNull
    private NovoPedido pedido;

    public boolean documentoValido() {
        Assert.hasLength(documento, "você não deveria validar o documento se ele não tiver sido preenchido");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null)
                || cnpjValidator.isValid(documento, null);

    }

    public Compra toModel(EntityManager manager) {

        @NotNull Pais pais = manager.find(Pais.class, idPais);

        Estado estado = null;
        if (idEstado != null) {
            estado = manager.find(Estado.class, idEstado);
            Assert.state(estado.pertenceAPais(pais), "este estado não é do país selecionado");
        }

        Pedido pedido = this.pedido.toModel(manager);

        return Compra.builder()
                .email(email)
                .nome(nome)
                .sobrenome(sobrenome)
                .documento(documento)
                .endereco(endereco)
                .complemento(complemento)
                .cidade(cidade)
                .pais(pais)
                .estado(estado)
                .telefone(telefone)
                .cep(cep)
                .pedido(pedido)
                .build();
    }
}
