package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoAtualizarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoBuscarFuncionariosException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoDeletarFuncionarioException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FalhaAoMudarRoleException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.RoleInvalidaException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AtualizarRoleDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.BuscarUsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.UsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorie repositorie;

	public ResponseEntity<String> atualizarUsuario(UsuarioDTO dto) {

		try {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();

			Usuario usuario = this.repositorie.findByEmail(email).orElseThrow(() -> new FuncionarioInvalidoException());

			usuario.setNome(dto.nome());

			String novaSenha = new BCryptPasswordEncoder().encode(dto.senha());

			usuario.setSenha(novaSenha);

			repositorie.save(usuario);

			return ResponseEntity.ok().body("Dados do usuario atualizados");

		} catch (Exception e) {
			// TODO: handle exception
			throw new FalhaAoAtualizarFuncionarioException();
		}

	}

	public ResponseEntity<String> mudarRole(AtualizarRoleDTO dto) {

		try {

			if (dto.role() == null) {
				throw new RoleInvalidaException();
			}

			Usuario usuario = this.repositorie.findByEmail(dto.email())
					.orElseThrow(() -> new FuncionarioInvalidoException());

			usuario.setRoleUsuario(dto.role());

			repositorie.save(usuario);

			return ResponseEntity.ok().body("Permissão alterada");

		} catch (Exception e) {
			// TODO: handle exception
			throw new FalhaAoMudarRoleException();
		}

	}

	public ResponseEntity<String> deletarUsuario() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario usuario = repositorie.findByEmail(email).orElseThrow(() -> new FuncionarioInvalidoException());

		try {

			repositorie.delete(usuario);

			return ResponseEntity.ok().body("Funcionario deletado");

		} catch (Exception e) {
			// TODO: handle exception
			throw new FalhaAoDeletarFuncionarioException();
		}

	}

	public List<BuscarUsuarioDTO> buscarUsuarios() {

		try {
			List<Usuario> usuarios = repositorie.findAll();

			List<BuscarUsuarioDTO> usuariosDTO = usuarios.stream()
					.map(e -> new BuscarUsuarioDTO(e.getNome(), e.getEmail(), e.getRoleUsuario())).toList();

			return usuariosDTO;

		} catch (Exception e) {
			throw new FalhaAoBuscarFuncionariosException();
		}

	}

}
