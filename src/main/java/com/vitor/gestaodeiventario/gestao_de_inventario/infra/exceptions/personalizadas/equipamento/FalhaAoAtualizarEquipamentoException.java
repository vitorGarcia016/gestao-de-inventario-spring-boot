package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento;

public class FalhaAoAtualizarEquipamentoException extends RuntimeException{

	public FalhaAoAtualizarEquipamentoException() {
		super("Falha ao atualizar equipamento");
	}
}
