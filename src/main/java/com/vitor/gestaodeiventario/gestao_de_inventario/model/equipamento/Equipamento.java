package com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String categoria;
	
	
	@Column(nullable = false)
	@NotNull
	private Integer quantidadeDisponivel;
	
	@NotBlank
	@Column(nullable = false)
	private String localArmazenamento;
	
	
	public Equipamento() {
		// TODO Auto-generated constructor stub
	}


	public Equipamento(@NotBlank String nome, @NotBlank String categoria, @NotNull Integer quantidadeDisponivel,
			@NotBlank String localArmazenamento) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.localArmazenamento = localArmazenamento;
	}


	@Override
	public int hashCode() {
		return Objects.hash(categoria, localArmazenamento, nome, quantidadeDisponivel);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		return Objects.equals(categoria, other.categoria)
				&& Objects.equals(localArmazenamento, other.localArmazenamento) && Objects.equals(nome, other.nome)
				&& Objects.equals(quantidadeDisponivel, other.quantidadeDisponivel);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}


	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}


	public String getLocalArmazenamento() {
		return localArmazenamento;
	}


	public void setLocalArmazenamento(String localArmazenamento) {
		this.localArmazenamento = localArmazenamento;
	}
	
	
	
}
