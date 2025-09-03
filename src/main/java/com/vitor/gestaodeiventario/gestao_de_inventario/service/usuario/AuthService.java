package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAdicionarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaRealizarLoginException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioJaExistenteException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.security.TokenService;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.RoleUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AuthDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.TokenDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService service;

	public ResponseEntity<String> registro(AuthDTO dto) {

		if (this.repositorie.findByEmail(dto.getEmail()).isPresent()) {
			throw new FuncionarioJaExistenteException();

		} else {

			try {
				String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.getSenha());

				Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), senhaCriptografada,
						RoleUsuario.FUNCIONARIO);

				repositorie.save(usuario);

				return ResponseEntity.ok().body("Funcionario adicionado");

			} catch (Exception e) {
				throw new FalhaAdicionarFuncionarioException();
			}
		}

	}

	public ResponseEntity<?> login(AuthDTO dto) {

		try {
			var usuarioToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

			var auth = authenticationManager.authenticate(usuarioToken);

			Optional<Usuario> usuario = repositorie.findByEmail(dto.getEmail());

			String token = service.gerarToken(usuario.orElseThrow(() -> new FuncionarioInvalidoException()));

			return ResponseEntity.ok(new TokenDTO(token));

		} catch (Exception e) {
			throw new FalhaRealizarLoginException();
		}

	}

}
