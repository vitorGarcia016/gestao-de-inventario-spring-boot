package com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.RoleUsuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarRoleDTO(@NotBlank @Email(message = "Digite um email valido") String email,
		@NotNull RoleUsuario role) {

}
