package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleUsuario roleUsuario;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusUsuario statusUsuario;

	public Usuario(String nome, String email, String senha, RoleUsuario roleUsuario, StatusUsuario statusUsuario) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.roleUsuario = roleUsuario;
		this.statusUsuario = statusUsuario;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, nome, roleUsuario, senha, statusUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(nome, other.nome)
				&& roleUsuario == other.roleUsuario && Objects.equals(senha, other.senha)
				&& statusUsuario == other.statusUsuario;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public RoleUsuario getRoleUsuario() {
		return roleUsuario;
	}

	public void setRoleUsuario(RoleUsuario roleUsuario) {
		this.roleUsuario = roleUsuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if (this.roleUsuario == RoleUsuario.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
		}
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
