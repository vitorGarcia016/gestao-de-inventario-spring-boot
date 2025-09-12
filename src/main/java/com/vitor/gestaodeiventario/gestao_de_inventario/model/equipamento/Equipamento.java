package com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String categoria;

	@Column(nullable = false)
	private String localArmazenamento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEquipamento status;

	public Equipamento() {
		// TODO Auto-generated constructor stub
	}

	public Equipamento(String nome, String categoria, String localArmazenamento, StatusEquipamento status) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.localArmazenamento = localArmazenamento;
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, localArmazenamento, nome, status);
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
				&& status == other.status;
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
