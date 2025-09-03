package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FalhaAoMudarRoleException extends RuntimeException{

	public FalhaAoMudarRoleException() {
		super("Falha ao mudar permissao do funcionario");
	}
}
