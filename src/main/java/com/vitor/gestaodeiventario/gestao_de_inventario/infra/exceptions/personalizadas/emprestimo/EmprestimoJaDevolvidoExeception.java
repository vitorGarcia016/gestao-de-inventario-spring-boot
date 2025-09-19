package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo;

public class EmprestimoJaDevolvidoExeception extends RuntimeException{

	public EmprestimoJaDevolvidoExeception() {
		// TODO Auto-generated constructor stub
		super("Emprestimo ja devolvido");
	}
}
