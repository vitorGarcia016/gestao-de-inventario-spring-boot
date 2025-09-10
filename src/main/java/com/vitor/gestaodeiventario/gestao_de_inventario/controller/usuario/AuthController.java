package com.vitor.gestaodeiventario.gestao_de_inventario.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.dtos.AuthDTO;
import com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/registro")
	public ResponseEntity<String> registro(@Valid @RequestBody AuthDTO dto) {
		return authService.registro(dto);
	}
	
	@PutMapping("/validar")
	public ResponseEntity<String> validarUsuario(@RequestBody String uuid){
		return authService.validarUuid(uuid);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthDTO dto) {
		return authService.login(dto);
	}

}
