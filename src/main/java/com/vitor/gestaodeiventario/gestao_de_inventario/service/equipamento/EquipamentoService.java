package com.vitor.gestaodeiventario.gestao_de_inventario.service.equipamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.dtos.EquipamentoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepositorie equipamentoRepositorie;

	public ResponseEntity<String> adicionarEquipamento(EquipamentoDTO dto) {

		Equipamento equipamento = new Equipamento(dto.getNome(), dto.getCategoria(), dto.getLocalArmazenamento(),
				dto.getStatus());

		equipamentoRepositorie.save(equipamento);

		return ResponseEntity.ok().body("Equipamento adicionado");

	}

	public ResponseEntity<String> atualizarEquipamento(EquipamentoDTO dto) {

		Equipamento equipamento = equipamentoRepositorie.findById(dto.getId())
				.orElseThrow(() -> new EquipamentoNaoEncontradoException());

		equipamento.setNome(dto.getNome());
		equipamento.setCategoria(dto.getCategoria());
		equipamento.setLocalArmazenamento(dto.getLocalArmazenamento());
		equipamento.setStatus(dto.getStatus());

		equipamentoRepositorie.save(equipamento);

		return ResponseEntity.ok().body("Equipamento atualizado");

	}

	public ResponseEntity<String> deletarEquipamento(Integer id) {

		Equipamento equipamento = equipamentoRepositorie.findById(id)
				.orElseThrow(() -> new EquipamentoNaoEncontradoException());

		equipamentoRepositorie.delete(equipamento);
		return ResponseEntity.ok().body("Equipamento deletado");

	}

	public ResponseEntity<?> obterEquipamentos(StatusEquipamento status) {

		List<Equipamento> equipamentos = equipamentoRepositorie.findAllByStatus(status);

		List<EquipamentoDTO> dto = equipamentos.stream().map(e -> new EquipamentoDTO(e.getId(), e.getNome(),
				e.getCategoria(), e.getLocalArmazenamento(), e.getStatus())).toList();

		if (dto.isEmpty()) {
			return ResponseEntity.ok().body("Nenhum equipamento encontrado");
		}

		return ResponseEntity.ok().body(dto);

	}

}
