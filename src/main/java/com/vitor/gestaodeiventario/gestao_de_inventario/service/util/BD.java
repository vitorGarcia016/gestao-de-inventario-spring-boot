package com.vitor.gestaodeiventario.gestao_de_inventario.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.infra.exceptions.personalizadas.usuario.FuncionarioInvalidoException;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

@Service
public class BD {

	@Autowired
	private UsuarioRepositorie usuarioRepositorie;

	public Usuario obterUsuarioDaVezBd() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Usuario usuario = usuarioRepositorie.findByEmail(email).orElseThrow(() -> new FuncionarioInvalidoException());

		return usuario;

	}
}
