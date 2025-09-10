package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class SenhaInvalidaException extends RuntimeException{

	public SenhaInvalidaException() {
		// TODO Auto-generated constructor stub
		super("Senha invalida");
	}
}
