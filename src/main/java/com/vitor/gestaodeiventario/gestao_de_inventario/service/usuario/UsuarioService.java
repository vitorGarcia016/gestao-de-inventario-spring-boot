package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.RoleInvalidaException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.SenhaInvalidaException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.StatusUsuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AtualizarRoleDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AtualizarSenhaDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.BuscarUsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.DeletarDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.UsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.email.EmailService;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.util.BD;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private BD util;

	@Autowired
	private EmailService emailService;

	public ResponseEntity<String> atualizarNome(UsuarioDTO dto) {

		Usuario usuario = util.obterUsuarioDaVezBd();

		if (!encoder.matches(dto.senha(), usuario.getSenha())) {
			throw new SenhaInvalidaException();
		}

		usuario.setNome(dto.nome());

		repositorie.save(usuario);

		return ResponseEntity.ok().body("Nome do usuario atualizado");

	}

	public ResponseEntity<String> recuperarSenha() {

		Usuario usuario = util.obterUsuarioDaVezBd();

		String senhaProvisoria = UUID.randomUUID().toString().substring(0, 6);

		String senhaCriptografa = new BCryptPasswordEncoder().encode(senhaProvisoria);

		usuario.setSenha(senhaCriptografa);

		repositorie.save(usuario);

		emailService.enviarEmail(usuario.getEmail(), "Nova senha de acesso",
				"A sua senha provisória é: " + senhaProvisoria);

		return ResponseEntity.ok().body("Senha enviada para o seu email");

	}

	public ResponseEntity<String> mudarRole(AtualizarRoleDTO dto) {

		if (dto.role() == null) {
			throw new RoleInvalidaException();
		}

		Usuario usuario = this.repositorie.findByEmail(dto.email())
				.orElseThrow(() -> new FuncionarioInvalidoException());

		usuario.setRoleUsuario(dto.role());

		repositorie.save(usuario);

		return ResponseEntity.ok().body("Permissão alterada");

	}

	public ResponseEntity<String> atualizarSenha(AtualizarSenhaDTO dto) {

		Usuario usuario = util.obterUsuarioDaVezBd();

		if (!encoder.matches(dto.senhaAntiga(), usuario.getSenha())) {
			throw new SenhaInvalidaException();
		}

		if (dto.senhaAntiga().equals(dto.senhaNova())) {
			throw new SenhaInvalidaException();
		}

		String senhaCriptografada = encoder.encode(dto.senhaNova());

		usuario.setSenha(senhaCriptografada);

		repositorie.save(usuario);

		return ResponseEntity.ok().body("Senha atualizada com sucesso");

	}

	public ResponseEntity<String> deletarUsuario(DeletarDTO dto) {

		Usuario usuario = util.obterUsuarioDaVezBd();

		if (!encoder.matches(dto.senha(), usuario.getSenha())) {
			throw new SenhaInvalidaException();
		}

		repositorie.delete(usuario);

		return ResponseEntity.ok().body("Funcionario deletado");

	}

	public ResponseEntity<List<BuscarUsuarioDTO>> buscarUsuarios() {

		List<Usuario> usuarios = repositorie.findAllByStatusUsuario(StatusUsuario.ATIVO);

		List<BuscarUsuarioDTO> usuariosDTO = usuarios.stream()
				.map(e -> new BuscarUsuarioDTO(e.getNome(), e.getEmail(), e.getRoleUsuario())).toList();

		return ResponseEntity.ok().body(usuariosDTO);

	}

}
