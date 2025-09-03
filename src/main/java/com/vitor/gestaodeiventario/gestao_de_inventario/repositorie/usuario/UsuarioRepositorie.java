package com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.gestaodeiventario.gestao_de_inventario.model.usuario.Usuario;

@Repository
public interface UsuarioRepositorie extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

}
