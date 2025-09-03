package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FalhaAoDeletarFuncionarioException extends RuntimeException{

	public FalhaAoDeletarFuncionarioException() {
		// TODO Auto-generated constructor stub
		super("Falha ao deletar funcionario");
	}
}
