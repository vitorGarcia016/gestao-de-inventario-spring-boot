package com.vitor.gestaodeiventario.gestao_de_inventario.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;
	
	
	public String gerarToken(Usuario usuario) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT
					.create()
					.withIssuer("minha-api")
					.withSubject(usuario.getEmail())
					.withExpiresAt(tempoToken())
					.sign(algorithm);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Falha ao gerar Token" + e.getMessage());
		}
		
	}
	
	public String obterUsuarioToken(String token) {
		
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT
					.require(algorithm)
					.withIssuer("minha-api")
					.build()
					.verify(token)
					.getSubject();
					
			
		} catch (Exception e) {
			throw new RuntimeException("Falha ao obter usuario pelo token");
			
		}
	}
	
	
	
	private Instant tempoToken() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
