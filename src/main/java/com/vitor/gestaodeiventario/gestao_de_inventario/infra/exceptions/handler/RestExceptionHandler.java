package com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EmprestimoJaDevolvidoExeception;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EmprestimoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.EquipamentoIndisponivelException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.emprestimo.FuncionarioNaoCorrespondenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.equipamento.EquipamentoNaoEncontradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioJaExistenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.RoleInvalidaException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.SenhaInvalidaException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.UuidInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.UuidexpiradoException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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

	@ExceptionHandler(SenhaInvalidaException.class)
	private ResponseEntity<?> SenhaInvalidaHandler(SenhaInvalidaException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(RoleInvalidaException.class)
	private ResponseEntity<?> RoleInvalidaHandler(RoleInvalidaException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EquipamentoNaoEncontradoException.class)
	private ResponseEntity<?> EquipamentoNaoEncontradoHandler(EquipamentoNaoEncontradoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EquipamentoIndisponivelException.class)
	private ResponseEntity<?> EquipamentoIndisponivelHandler(EquipamentoIndisponivelException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EmprestimoNaoEncontradoException.class)
	private ResponseEntity<?> EmprestimoNaoEncontradoHandler(EmprestimoNaoEncontradoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(FuncionarioNaoCorrespondenteException.class)
	private ResponseEntity<?> FuncionarioNaoCorrespondenteHandler(FuncionarioNaoCorrespondenteException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(UuidInvalidoException.class)
	private ResponseEntity<?> UuidInvalidoHandler(UuidInvalidoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(UuidexpiradoException.class)
	private ResponseEntity<?> UuidexpiradoHandler(UuidexpiradoException e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

	@ExceptionHandler(EmprestimoJaDevolvidoExeception.class)
	private ResponseEntity<?> EmprestimoJaDevolvidoHandler(EmprestimoJaDevolvidoExeception e) {
		MensagemErro mensagemErro = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);

	}

}
