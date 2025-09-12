package com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.dtos;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoDTO {

	@NotNull
	private Integer id;

	@NotBlank
	private String nome;

	@NotBlank
	private String categoria;

	@NotBlank
	private String localArmazenamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEquipamento status;

	public EquipamentoDTO(@NotBlank String nome, @NotBlank String categoria, @NotBlank String localArmazenamento,
			@NotNull StatusEquipamento status) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.localArmazenamento = localArmazenamento;
		this.status = status;

	}

	public EquipamentoDTO(@NotNull Integer id, @NotBlank String nome, @NotBlank String categoria,
			@NotBlank String localArmazenamento, @NotNull StatusEquipamento status) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.localArmazenamento = localArmazenamento;
		this.status = status;
	}

	public EquipamentoDTO() {
		// TODO Auto-generated constructor stub
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

	public String getLocalArmazenamento() {
		return localArmazenamento;
	}

	public void setLocalArmazenamento(String localArmazenamento) {
		this.localArmazenamento = localArmazenamento;
	}

	public StatusEquipamento getStatus() {
		return status;
	}

	public void setStatus(StatusEquipamento status) {
		this.status = status;
	}

}
