package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.email;

public class FalhaAoEnviarEmailException extends RuntimeException{

	public FalhaAoEnviarEmailException() {
		// TODO Auto-generated constructor stub
		super("Falha ao enviar email");
	}
}
