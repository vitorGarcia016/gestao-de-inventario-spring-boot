package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo;

public class EmprestimoNaoEncontradoException extends RuntimeException{

	public EmprestimoNaoEncontradoException() {
		// TODO Auto-generated constructor stub
		super("Emprestimo nao encontrado");
	}
}
