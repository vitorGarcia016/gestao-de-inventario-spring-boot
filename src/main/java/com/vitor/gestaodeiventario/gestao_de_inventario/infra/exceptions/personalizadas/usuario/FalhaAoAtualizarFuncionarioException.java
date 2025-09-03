package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FalhaAoAtualizarFuncionarioException extends RuntimeException{

	public FalhaAoAtualizarFuncionarioException() {
		super("Falha ao atualizar funcionario");
	}
}
