package com.vitor.gestaodeiventario.gestao_de_inventario.infra.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;
import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

	@Autowired
	private TokenService service;

	@Autowired
	private UsuarioRepositorie repositorie;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = obterToken(request);

		if (token != null) {

			String email = service.obterUsuarioToken(token);

			Usuario usuario = repositorie.findByEmail(email)
					.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

			var usuarioToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(usuarioToken);
			

		}

		filterChain.doFilter(request, response);

	}

	private String obterToken(HttpServletRequest httpServletRequest) {

		String token = httpServletRequest.getHeader("Authorization");

		if (token != null) {
			return token.replace("Bearer ", "");
		} else {
			return null;
		}
	}
}
