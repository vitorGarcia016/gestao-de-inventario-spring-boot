package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FalhaRealizarLoginException extends RuntimeException{

	public FalhaRealizarLoginException() {
		// TODO Auto-generated constructor stub
		super("Falha ao realizar login");
	}
}
