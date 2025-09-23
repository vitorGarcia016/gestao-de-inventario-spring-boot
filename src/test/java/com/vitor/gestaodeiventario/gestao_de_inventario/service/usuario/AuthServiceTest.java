package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioJaExistenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.UuidInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.UuidexpiradoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.security.TokenService;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.RoleUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.StatusUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.UsuarioValidacao;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AuthDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioValidacaoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.email.EmailService;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@Mock
	private UsuarioRepositorie Usuariorepositorie;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private UsuarioValidacaoRepositorie usuarioValidacaoRepositorie;

	@Mock
	private TokenService service;

	@Mock
	private EmailService emailService;

	@InjectMocks
	private AuthService authService;

	@Test
	@DisplayName("Sucesso ao fazer registro")
	void testRegistroCase1() {

		AuthDTO authDTO = new AuthDTO("vitor", "vitor@gmail.com", "123");

		when(Usuariorepositorie.findByEmail(any())).thenReturn(Optional.empty());

		ResponseEntity<String> resposta = authService.registro(authDTO);

		assertTrue(resposta.getBody().contains("Agora verifique o seu email"));
		verify(Usuariorepositorie).save(any());
		verify(usuarioValidacaoRepositorie).save(any());
		verify(emailService).enviarEmail(any(), any(), any());

	}

	@Test
	@DisplayName("Deve lancar uma FuncionarioJaExistenteException")
	void testRegistroCase2() {

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		AuthDTO authDTO = new AuthDTO("vitor", "vito@gmail.com", "123");

		when(Usuariorepositorie.findByEmail(any())).thenReturn(Optional.of(usuario));

		assertThrows(FuncionarioJaExistenteException.class, () -> authService.registro(authDTO));

		verify(Usuariorepositorie, never()).save(any());
		verify(usuarioValidacaoRepositorie, never()).save(any());
		verify(emailService, never()).enviarEmail(any(), any(), any());

	}

	@Test
	@DisplayName("Sucesso ao validar UUID")
	void validarUuidCase1() {

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);

		UsuarioValidacao usuarioValidacao = new UsuarioValidacao("123", Instant.now().plusMillis(600000), usuario);

		when(usuarioValidacaoRepositorie.findByUuid("123")).thenReturn(Optional.of(usuarioValidacao));

		when(Usuariorepositorie.findById(usuarioValidacao.getUsuario().getId())).thenReturn(Optional.of(usuario));

		ResponseEntity<String> resposta = authService.validarUuid("123");

		assertTrue(resposta.getBody().contains("Validado com sucesso"));
		verify(Usuariorepositorie).save(any());
		verify(usuarioValidacaoRepositorie).delete(any());

	}

	@Test
	@DisplayName("Deve lancar uma UuidInvalidoException")
	void validarUuidCase2() {

		when(usuarioValidacaoRepositorie.findByUuid("123")).thenReturn(Optional.empty());

		assertThrows(UuidInvalidoException.class, () -> authService.validarUuid("123"));
		verify(Usuariorepositorie, never()).save(any());

	}

	@Test
	@DisplayName("Deve lancar uma FuncionarioInvalidoException")
	void validarUuidCase3() {

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);

		UsuarioValidacao usuarioValidacao = new UsuarioValidacao("123", Instant.now().plusMillis(600000), usuario);

		when(usuarioValidacaoRepositorie.findByUuid("123")).thenReturn(Optional.of(usuarioValidacao));

		when(Usuariorepositorie.findById(usuarioValidacao.getUsuario().getId())).thenReturn(Optional.empty());

		assertThrows(FuncionarioInvalidoException.class, () -> authService.validarUuid("123"));
		verify(Usuariorepositorie, never()).save(any());

	}

	@Test
	@DisplayName("Deve lancar uma UuidexpiradoException")
	void validarUuidCase4() {

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);
		usuario.setId(1);

		UsuarioValidacao usuarioValidacao = new UsuarioValidacao("123", Instant.now().minusMillis(70000), usuario);

		when(usuarioValidacaoRepositorie.findByUuid("123")).thenReturn(Optional.of(usuarioValidacao));

		when(Usuariorepositorie.findById(usuarioValidacao.getUsuario().getId())).thenReturn(Optional.of(usuario));

		assertThrows(UuidexpiradoException.class, () -> authService.validarUuid("123"));

		verify(Usuariorepositorie, never()).save(any());

	}

	@Test
	@DisplayName("Sucesso ao fazer login")
	void loginCase1() {

		AuthDTO authDTO = new AuthDTO("vitor", "vito@gmail.com", "123");

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.ATIVO);

		when(Usuariorepositorie.findByEmail(authDTO.getEmail())).thenReturn(Optional.of(usuario));

		ResponseEntity<?> resposta = authService.login(authDTO);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		verify(service).gerarToken(usuario);

	}

	@Test
	@DisplayName("Deve retornar uma FuncionarioInvalidoException ao buscar funcionario")
	void loginCase2() {

		AuthDTO authDTO = new AuthDTO("vitor", "vito@gmail.com", "123");

		when(Usuariorepositorie.findByEmail(authDTO.getEmail())).thenReturn(Optional.empty());

		assertThrows(FuncionarioInvalidoException.class, () -> authService.login(authDTO));

		verify(service, never()).gerarToken(any());

	}

	@Test
	@DisplayName("Deve retornar uma FuncionarioInvalidoException ao verificar status inativo do funcionario")
	void loginCase3() {

		AuthDTO authDTO = new AuthDTO("vitor", "vito@gmail.com", "123");

		Usuario usuario = new Usuario("vitor", "vito@gmail.com", "123", RoleUsuario.FUNCIONARIO, StatusUsuario.INATIVO);

		when(Usuariorepositorie.findByEmail(authDTO.getEmail())).thenReturn(Optional.of(usuario));

		assertThrows(FuncionarioInvalidoException.class, () -> authService.login(authDTO));

		verify(service, never()).gerarToken(any());

	}

}
