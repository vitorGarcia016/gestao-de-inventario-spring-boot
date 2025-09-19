package com.vitor.gestaodeiventario.gestao_de_inventario.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AtualizarRoleDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AtualizarSenhaDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.BuscarUsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.DeletarDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.UsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PutMapping("/atualizar/nome")
	public ResponseEntity<String> putNome(@Valid @RequestBody UsuarioDTO dto) {

		return service.atualizarNome(dto);
	}
	
	@PutMapping("/atualizar/senha")
	public ResponseEntity<String> putSenha(@Valid @RequestBody AtualizarSenhaDTO dto){
		return service.atualizarSenha(dto);
	}

	@GetMapping("/recuperar-senha")
	public ResponseEntity<String> getSenha() {

		return service.recuperarSenha();
	}

	@PutMapping("/permissoes")
	public ResponseEntity<String> putPermissoes(@Valid @RequestBody AtualizarRoleDTO dto) {

		return service.mudarRole(dto);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUsuario(@RequestBody @Valid DeletarDTO dto) {
		return service.deletarUsuario(dto);
	}

	@GetMapping
	public ResponseEntity<List<BuscarUsuarioDTO>> getUsuarios() {
		return service.buscarUsuarios();
	}

}
