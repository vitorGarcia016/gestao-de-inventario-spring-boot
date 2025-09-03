package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FuncionarioJaExistenteException extends RuntimeException{

	public FuncionarioJaExistenteException() {
		// TODO Auto-generated constructor stub
		super("Funcionario ja existente");
	}
}
