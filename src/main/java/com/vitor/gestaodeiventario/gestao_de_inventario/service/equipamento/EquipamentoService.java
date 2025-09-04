package com.vitor.gestaodeiventario.gestao_de_inventario.service.equipamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoAdicionarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoAtualizarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.dtos.EquipamentoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepositorie equipamentoRepositorie;

	public ResponseEntity<String> adicionarEquipamento(EquipamentoDTO dto) {

		try {

			Equipamento equipamento = new Equipamento(dto.getNome(), dto.getCategoria(), dto.getLocalArmazenamento(),
					dto.getStatus());

			equipamentoRepositorie.save(equipamento);

			return ResponseEntity.ok().body("Equipamento adicionado");

		} catch (Exception e) {

			throw new FalhaAoAdicionarEquipamentoException();
		}

	}

	public ResponseEntity<String> atualizarEquipamento(EquipamentoDTO dto) {

		Equipamento equipamento = equipamentoRepositorie.findById(dto.getId())
				.orElseThrow(() -> new EquipamentoNaoEncontradoException());

		try {

			equipamento.setNome(dto.getNome());
			equipamento.setCategoria(dto.getCategoria());
			equipamento.setLocalArmazenamento(dto.getLocalArmazenamento());
			equipamento.setStatus(dto.getStatus());

			equipamentoRepositorie.save(equipamento);

			return ResponseEntity.ok().body("Equipamento atualizado");

		} catch (Exception e) {
			// TODO: handle exception
			throw new FalhaAoAtualizarEquipamentoException();
		}

	}

}
