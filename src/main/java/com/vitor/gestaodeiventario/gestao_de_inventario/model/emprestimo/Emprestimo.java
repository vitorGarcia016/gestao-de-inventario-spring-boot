package com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo;

import java.time.LocalDate;
import java.util.Objects;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Equipamento equipamento;

	@Column(nullable = false)
	@NotNull
	private LocalDate dataEmprestimo;

	@Column(nullable = false)
	@NotNull
	private LocalDate dataPrevista;

	private LocalDate dataDevolvolucao;

	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusEmprestimo status;

	public Emprestimo() {
		// TODO Auto-generated constructor stub
	}

	public Emprestimo(@NotNull Usuario usuario, @NotNull Equipamento equipamento, @NotNull LocalDate dataEmprestimo,
			@NotNull LocalDate dataDevolucao, LocalDate dataDevolvolucao, StatusEmprestimo status) {
		super();
		this.usuario = usuario;
		this.equipamento = equipamento;
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevista = dataDevolucao;
		this.dataDevolvolucao = dataDevolucao;
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataPrevista, dataEmprestimo, equipamento, status, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(dataPrevista, other.dataPrevista) && Objects.equals(dataEmprestimo, other.dataEmprestimo)
				&& Objects.equals(equipamento, other.equipamento) && status == other.status
				&& Objects.equals(usuario, other.usuario);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public LocalDate getDataDevolvolucao() {
		return dataDevolvolucao;
	}

	public void setDataDevolvolucao(LocalDate dataDevolvolucao) {
		this.dataDevolvolucao = dataDevolvolucao;
	}

	public StatusEmprestimo getStatus() {
		return status;
	}

	public void setStatus(StatusEmprestimo status) {
		this.status = status;
	}

	
	
	

}
