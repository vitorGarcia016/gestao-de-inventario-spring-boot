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
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.BuscarUsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.UsuarioDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PutMapping
	public ResponseEntity<String> putUsuario(@Valid @RequestBody UsuarioDTO dto) {

		return service.atualizarUsuario(dto);
	}

	@PutMapping("/permissoes")
	public ResponseEntity<String> putPermissoes(@Valid @RequestBody AtualizarRoleDTO dto) {

		return service.mudarRole(dto);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUsuario() {
		return service.deletarUsuario();
	}

	@GetMapping
	public List<BuscarUsuarioDTO> getUsuarios() {
		return service.buscarUsuarios();
	}

}
