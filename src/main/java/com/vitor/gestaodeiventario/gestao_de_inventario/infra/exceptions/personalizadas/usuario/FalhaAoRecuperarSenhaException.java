package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario;

public class FalhaAoRecuperarSenhaException extends RuntimeException{

	public FalhaAoRecuperarSenhaException() {
		// TODO Auto-generated constructor stub
		super("Falha ao recuperar senha");
	}
}
