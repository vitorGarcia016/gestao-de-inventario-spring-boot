package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.handler;

import org.springframework.http.HttpStatus;

public class MensagemErro {

	private HttpStatus httpStatus;
	
	private String mensagem;
	
	
	public MensagemErro(HttpStatus httpStatus, String mensagem) {
		// TODO Auto-generated constructor stub
		
		this.httpStatus = httpStatus;
		this.mensagem = mensagem;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
