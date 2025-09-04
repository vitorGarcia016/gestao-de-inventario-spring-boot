package com.vitor.gestaodeiventario.gestao_de_inventario.controller.equipamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.dtos.EquipamentoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.equipamento.EquipamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

	@Autowired
	private EquipamentoService equipamentoService;
	
	@PostMapping("/adicionar")
	public ResponseEntity<String> postEquipamento(@RequestBody @Valid EquipamentoDTO dto){
		return equipamentoService.adicionarEquipamento(dto);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<String> putEquipamento(@RequestBody @Valid EquipamentoDTO dto){
		return equipamentoService.atualizarEquipamento(dto);
	}
}
