package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo;

public class FuncionarioNaoCorrespondenteException extends RuntimeException{

	public FuncionarioNaoCorrespondenteException() {
		// TODO Auto-generated constructor stub
		super("Funcionario não realizou esse emprestimo");
	}
}
