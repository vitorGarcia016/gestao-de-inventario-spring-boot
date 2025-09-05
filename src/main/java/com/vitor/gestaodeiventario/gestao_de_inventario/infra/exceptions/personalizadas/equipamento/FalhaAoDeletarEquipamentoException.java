package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento;

public class FalhaAoDeletarEquipamentoException extends RuntimeException{

	public FalhaAoDeletarEquipamentoException() {
		// TODO Auto-generated constructor stub
		super("Falha ao deletar equipamento");
	}
}
