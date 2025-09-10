package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioValidacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private Instant instant;

	@ManyToOne
	private Usuario usuario;

	public UsuarioValidacao() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioValidacao(String uuid, Instant instant, Usuario usuario) {
		super();
		this.uuid = uuid;
		this.instant = instant;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(instant, usuario, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioValidacao other = (UsuarioValidacao) obj;
		return Objects.equals(instant, other.instant) && Objects.equals(usuario, other.usuario)
				&& Objects.equals(uuid, other.uuid);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
