package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public class AuthDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	public AuthDTO() {
		// TODO Auto-generated constructor stub
	}

	public AuthDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public AuthDTO(@NotBlank String email, @NotBlank String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
