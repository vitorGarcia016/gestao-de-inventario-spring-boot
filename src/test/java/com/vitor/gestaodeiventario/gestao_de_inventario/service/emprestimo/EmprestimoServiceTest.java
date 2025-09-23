package com.vitor.gestaodeiventario.gestao_de_inventario.service.emprestimo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EmprestimoJaDevolvidoExeception;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FuncionarioNaoCorrespondenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.Emprestimo;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.emprestimo.StatusEmprestimo;
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

		Equipamento equipamento = new Equipamento("mesa", "assento", "ala b", StatusEquipamento.INDISPONIVEL);

		when(equipamentoRepositorie.findById(1)).thenReturn(Optional.of(equipamento));

		assertThrows(EquipamentoIndisponivelException.class, () -> emprestimoService.solicitarEmprestimo(1));

		verify(emprestimoRepositorie, never()).save(any());
	}

	@Test
	@DisplayName("Sucesso ao devolver emprestimo DEVOLVIDO")
	void devolverEmprestimoCase1() {

		Usuario usuario = new Usuario("vitor", "vitor@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);
		when(util.obterUsuarioDaVezBd()).thenReturn(usuario);

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		emprestimo.setStatus(StatusEmprestimo.PENDENTE);
		emprestimo.setDataPrevista(LocalDate.now().plusDays(1));

		when(emprestimoRepositorie.findById(1)).thenReturn(Optional.of(emprestimo));

		ResponseEntity<String> resposta = emprestimoService.devolverEmprestimo(1);

		assertTrue(resposta.getBody().contains("DEVOLVIDO"));
		verify(emprestimoRepositorie).save(emprestimo);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	@Test
	@DisplayName("Sucesso ao devolver emprestimo com status ATRASADO")
	void devolverEmprestimoCase2() {

		Usuario usuario = new Usuario("vitor", "vitor@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);
		when(util.obterUsuarioDaVezBd()).thenReturn(usuario);

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		emprestimo.setStatus(StatusEmprestimo.PENDENTE);
		emprestimo.setDataPrevista(LocalDate.now().minusDays(1));

		when(emprestimoRepositorie.findById(1)).thenReturn(Optional.of(emprestimo));

		ResponseEntity<String> resposta = emprestimoService.devolverEmprestimo(1);

		assertTrue(resposta.getBody().contains("ATRASADO"));
		verify(emprestimoRepositorie).save(emprestimo);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	@Test
	@DisplayName("Deve retornar uma EmprestimoJaDevolvidoExeception")
	void devolverEmprestimoCase3() {

		Usuario usuario = new Usuario("vitor", "vitor@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);
		when(util.obterUsuarioDaVezBd()).thenReturn(usuario);

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
		emprestimo.setDataPrevista(LocalDate.now().plusDays(1));

		when(emprestimoRepositorie.findById(1)).thenReturn(Optional.of(emprestimo));

		assertThrows(EmprestimoJaDevolvidoExeception.class, () -> emprestimoService.devolverEmprestimo(1));

		verify(emprestimoRepositorie, never()).save(emprestimo);

	}

	@Test
	@DisplayName("Deve retornar uma FuncionarioNaoCorrespondenteException")
	void devolverEmprestimoCase4() {

		Usuario usuario = new Usuario("vitor", "vitor@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);
		when(util.obterUsuarioDaVezBd()).thenReturn(usuario);

		Usuario outroUsuario = new Usuario("Joao", "joao@gmail.com", "123", RoleUsuario.FUNCIONARIO,
				StatusUsuario.ATIVO);
		outroUsuario.setId(2);

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setUsuario(outroUsuario);
		emprestimo.setStatus(StatusEmprestimo.PENDENTE);
		emprestimo.setDataPrevista(LocalDate.now().plusDays(1));

		when(emprestimoRepositorie.findById(1)).thenReturn(Optional.of(emprestimo));

		assertThrows(FuncionarioNaoCorrespondenteException.class, () -> emprestimoService.devolverEmprestimo(1));

		verify(emprestimoRepositorie, never()).save(emprestimo);

	}

}
