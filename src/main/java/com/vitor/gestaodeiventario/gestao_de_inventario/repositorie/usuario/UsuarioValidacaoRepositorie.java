package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.UsuarioValidacao;

public interface UsuarioValidacaoRepositorie extends JpaRepository<UsuarioValidacao, Integer> {

	
	Optional<UsuarioValidacao> findByUuid(String uuid);
}
