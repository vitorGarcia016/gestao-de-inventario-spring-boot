package com.vitor.gestaodeiventario.gestao_de_inventario.service.equipamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.dtos.EquipamentoDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;

@ExtendWith(MockitoExtension.class)
class EquipamentoServiceTest {

	@Mock
	private EquipamentoRepositorie equipamentoRepositorie;

	@InjectMocks
	private EquipamentoService equipamentoService;

	@Test
	void adicionarEquipamento() {

		EquipamentoDTO equipamento = new EquipamentoDTO("lapis", "escolar", "ala b", StatusEquipamento.DISPONIVEL);

		ResponseEntity<String> resposta = equipamentoService.adicionarEquipamento(equipamento);

		verify(equipamentoRepositorie).save(any());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("Equipamento adicionado"));

	}

	@Test
	@DisplayName("Sucesso ao atualizar equipamento")
	void atualizarEquipamentoCase1() {

		Equipamento equipamento = new Equipamento("lapis", "escolar", "ala b", StatusEquipamento.DISPONIVEL);
		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.of(equipamento));

		EquipamentoDTO equipamentoDTO = new EquipamentoDTO(1, "lapis", "escolar", "ala b",
				StatusEquipamento.DISPONIVEL);

		ResponseEntity<String> resposta = equipamentoService.atualizarEquipamento(equipamentoDTO);

		verify(equipamentoRepositorie).save(equipamento);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains("Equipamento atualizado"));

	}

	@Test
	@DisplayName("Deve lancar uma EquipamentoNaoEncontradoException")
	void atualizarEquipamentoCase2() {

		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.empty());

		EquipamentoDTO equipamentoDTO = new EquipamentoDTO(1, "lapis", "escolar", "ala b",
				StatusEquipamento.DISPONIVEL);

		assertThrows(EquipamentoNaoEncontradoException.class,
				() -> equipamentoService.atualizarEquipamento(equipamentoDTO));
		verify(equipamentoRepositorie, never()).save(any());

	}

	@Test
	@DisplayName("Sucesso ao deletar equipamento")
	void deletarEquipamentoCase1() {

		Equipamento equipamento = new Equipamento("lapis", "escolar", "ala b", StatusEquipamento.DISPONIVEL);

		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.of(equipamento));

		ResponseEntity<String> resposta = equipamentoService.deletarEquipamento(1);

		assertTrue(resposta.getBody().contains("Equipamento deletado"));
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		verify(equipamentoRepositorie).delete(any());

	}

	@Test
	@DisplayName("Deletar equipamento: Deve lancar uma EquipamentoNaoEncontradoException")
	void deletarEquipamentoCase2() {

		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.empty());

		assertThrows(EquipamentoNaoEncontradoException.class, () -> equipamentoService.deletarEquipamento(1));

		verify(equipamentoRepositorie, never()).delete(any());

	}

}
