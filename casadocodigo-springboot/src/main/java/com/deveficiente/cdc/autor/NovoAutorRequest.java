package com.deveficiente.cdc.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NovoAutorRequest {

	@NotBlank(message = "{beanvalidation.campo.vazio}")
	private String nome;

	@NotBlank(message = "{beanvalidation.campo.vazio}")
	@Email(message = "{beanvalidation.email.invalido}")
	private String email;

	@NotBlank(message = "{beanvalidation.campo.vazio}")

	@Size(max = 400, message = "{beanvalidation.tamanho.maximo}")
	private String descricao;

	public NovoAutorRequest(@NotBlank String nome,
			@NotBlank @Email String email,
			@NotBlank @Size(max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(this.nome,this.email,this.descricao);
	}

	public String getEmail() {
		return this.email;
	}

}
