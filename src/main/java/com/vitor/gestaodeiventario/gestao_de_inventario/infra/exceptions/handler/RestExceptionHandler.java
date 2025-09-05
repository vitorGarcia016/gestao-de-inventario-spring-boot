package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoObterEmprestimosException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FalhaAoSolicitarEmprestimoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoAdicionarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoAtualizarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoDeletarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.FalhaAoPesquisarEquipamentoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAdicionarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoAtualizarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoBuscarFuncionariosException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoDeletarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoMudarRoleException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaRealizarLoginException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioJaExistenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.RoleInvalidaException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FalhaAdicionarFuncionarioException.class)
	private ResponseEntity<?> falhaAdicionarFuncionarioHandler(FalhaAdicionarFuncionarioException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}

	@ExceptionHandler(FalhaRealizarLoginException.class)
	private ResponseEntity<?> falhaRealizarLoginHandler(FalhaRealizarLoginException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}

	@ExceptionHandler(FuncionarioInvalidoException.class)
	private ResponseEntity<?> funcionarioInvalidoHandler(FuncionarioInvalidoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}

	@ExceptionHandler(FuncionarioJaExistenteException.class)
	private ResponseEntity<?> FuncionarioJaExistenteHandler(FuncionarioJaExistenteException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}

	@ExceptionHandler(FalhaAoAtualizarFuncionarioException.class)
	private ResponseEntity<?> FalhaAoAtualizarFuncionarioHandler(FalhaAoAtualizarFuncionarioException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(RoleInvalidaException.class)
	private ResponseEntity<?> RoleInvalidaHandler(RoleInvalidaException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoMudarRoleException.class)
	private ResponseEntity<?> FalhaAoMudarRoleHandler(FalhaAoMudarRoleException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoDeletarFuncionarioException.class)
	private ResponseEntity<?> FalhaAoDeletarFuncionarioHandler(FalhaAoDeletarFuncionarioException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoBuscarFuncionariosException.class)
	private ResponseEntity<?> FalhaAoBuscarFuncionariosHandler(FalhaAoBuscarFuncionariosException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoAdicionarEquipamentoException.class)
	private ResponseEntity<?> FalhaAoAdicionarEquipamentoHandler(FalhaAoAdicionarEquipamentoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EquipamentoNaoEncontradoException.class)
	private ResponseEntity<?> EquipamentoNaoEncontradoHandler(EquipamentoNaoEncontradoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoAtualizarEquipamentoException.class)
	private ResponseEntity<?> FalhaAoAtualizarEquipamentoHandler(FalhaAoAtualizarEquipamentoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoPesquisarEquipamentoException.class)
	private ResponseEntity<?> FalhaAoPesquisarEquipamentoHandler(FalhaAoPesquisarEquipamentoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoDeletarEquipamentoException.class)
	private ResponseEntity<?> FalhaAoDeletarEquipamentoHandler(FalhaAoDeletarEquipamentoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FalhaAoSolicitarEmprestimoException.class)
	private ResponseEntity<?> FalhaAoSolicitarEmprestimoHandler(FalhaAoSolicitarEmprestimoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EquipamentoIndisponivelException.class)
	private ResponseEntity<?> EquipamentoIndisponivelHandler(EquipamentoIndisponivelException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}
	@ExceptionHandler(FalhaAoObterEmprestimosException.class)
	private ResponseEntity<?> FalhaAoObterEmprestimosHandler(FalhaAoObterEmprestimosException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
		
	}

}
