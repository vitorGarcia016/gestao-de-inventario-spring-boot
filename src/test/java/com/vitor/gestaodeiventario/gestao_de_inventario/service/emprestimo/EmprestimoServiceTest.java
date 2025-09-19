package com.vitor.gestaodeiventario.gestao_de_inventario.service.emprestimo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.http.ResponseEntity;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.Equipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.equipamento.StatusEquipamento;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.RoleUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.StatusUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.emprestimo.EmprestimoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.equipamento.EquipamentoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.util.BD;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

	
	@InjectMocks
	private EmprestimoService emprestimoService;

	@Mock
	private EmprestimoRepositorie emprestimoRepositorie;

	@Mock
	private EquipamentoRepositorie equipamentoRepositorie;

	@Mock
	private BD util;

	@Test
	@DisplayName("Solicitar emprestimo: Sucesso ao solicitar emprestimo")
	void testSolicitarEmprestimoCase1() {
		
		Usuario usuario = new Usuario("vitor", "vitor@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		
		Equipamento equipamento = new Equipamento("mesa", "mesa", "ala b", StatusEquipamento.DISPONIVEL);
		
		
		when(util.obterUsuarioDaVezBd()).thenReturn(usuario);
		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.of(equipamento));
		
		
		ResponseEntity<String> resposta = emprestimoService.solicitarEmprestimo(1);
		
		assertEquals(StatusEquipamento.INDISPONIVEL, equipamento.getStatus());
		assertThat(resposta.getBody().contains("Solicitacao enviada")).isTrue();
		verify(emprestimoRepositorie).save(any(Emprestimo.class));
		
	}
	
	@Test
	@DisplayName("Solicitar emprestimo: Deve lancar uma excessao quando o equipamento nao for encontrado")
	void testSolicitarEmprestimoCase2() {
		
		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(EquipamentoNaoEncontradoException.class, () -> emprestimoService.solicitarEmprestimo(1));
		
		verify(emprestimoRepositorie, never()).save(any());
		
	}
	
	@Test
	@DisplayName("Solicitar emprestimo: Deve lancar uma excessao se o equipamento estiver indisponivel")
	void testSolicitarEmprestimoCase3() {
		
		Equipamento equipamento = new Equipamento("mesa", "mesa", "ala b", StatusEquipamento.INDISPONIVEL);
		
		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.of(equipamento));
		
		assertThrows(EquipamentoIndisponivelException.class, () -> emprestimoService.solicitarEmprestimo(1));
		
		verify(emprestimoRepositorie, never()).save(any());
	}

}













