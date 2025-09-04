package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento;

public class EquipamentoNaoEncontradoException extends RuntimeException{

	public EquipamentoNaoEncontradoException() {
		// TODO Auto-generated constructor stub
		super("Equipamento nao encontrado");
	}
}
