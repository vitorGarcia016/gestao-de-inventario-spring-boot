package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.TokenDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioValidacaoRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.email.EmailService;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioValidacaoRepositorie usuarioValidacaoRepositorie;

	@Autowired
	private TokenService service;

	@Autowired
	private EmailService emailService;

	public ResponseEntity<String> registro(AuthDTO dto) {

		if (this.repositorie.findByEmail(dto.getEmail()).isPresent()) {
			throw new FuncionarioJaExistenteException();

		}

		String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha());

		Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), senhaCriptografada, RoleUsuario.FUNCIONARIO,
				StatusUsuario.INATIVO);

		repositorie.save(usuario);

		String uuid = UUID.randomUUID().toString();

		Instant tempoValidacao = Instant.now().plusMillis(600000);

		UsuarioValidacao usuarioValidacao = new UsuarioValidacao(uuid, tempoValidacao, usuario);

		usuarioValidacaoRepositorie.save(usuarioValidacao);

		emailService.enviarEmail(usuario.getEmail(), "Realização de cadastro de login",
				"O seu codigo de validação é: " + uuid);

		return ResponseEntity.ok().body("Agora verifique o seu email");

	}

	public ResponseEntity<String> validarUuid(String uuid) {

		UsuarioValidacao usuarioValidacao = usuarioValidacaoRepositorie.findByUuid(uuid)
				.orElseThrow(() -> new UuidInvalidoException());

		Usuario usuario = repositorie.findById(usuarioValidacao.getUsuario().getId())
				.orElseThrow(() -> new FuncionarioInvalidoException());

		Instant horaAtual = Instant.now();

		if (horaAtual.isAfter(usuarioValidacao.getInstant())) {
			usuarioValidacaoRepositorie.delete(usuarioValidacao);

			if (usuario.getStatusUsuario() == StatusUsuario.INATIVO) {
				repositorie.delete(usuario);

			}

			throw new UuidexpiradoException();
		}

		usuario.setStatusUsuario(StatusUsuario.ATIVO);

		repositorie.save(usuario);

		usuarioValidacaoRepositorie.delete(usuarioValidacao);

		return ResponseEntity.ok().body("Validado com sucesso");

	}

	public ResponseEntity<?> login(AuthDTO dto) {

		var usuarioToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

		authenticationManager.authenticate(usuarioToken);

		Usuario usuario = repositorie.findByEmail(dto.getEmail()).orElseThrow(() -> new FuncionarioInvalidoException());

		if (usuario.getStatusUsuario() != StatusUsuario.ATIVO) {
			throw new FuncionarioInvalidoException();
		}

		String token = service.gerarToken(usuario);

		return ResponseEntity.ok(new TokenDTO(token));

	}

}
