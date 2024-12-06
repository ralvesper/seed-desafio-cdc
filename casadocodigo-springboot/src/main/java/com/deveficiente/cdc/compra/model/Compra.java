package com.deveficiente.cdc.compra.model;

import com.deveficiente.cdc.cupons.Cupom;
import com.deveficiente.cdc.cupons.CupomAplicado;
import com.deveficiente.cdc.paisestado.Estado;
import com.deveficiente.cdc.paisestado.Pais;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Compra {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

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
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Estado estado;
    @OneToOne
    private Pedido pedido;
    @Embedded
    private CupomAplicado cupomAplicado;

    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;

    public void aplicaCupom(Cupom cupom) {
        this.cupomAplicado = new CupomAplicado(cupom);
        //this.pedido.aplicaCupom(cupom);
    }

   public void calculaCupom() {
        if (this.cupomAplicado != null) {
            this.pedido.aplicaCupom(this.cupomAplicado.getCupom());
        }
    }
}
