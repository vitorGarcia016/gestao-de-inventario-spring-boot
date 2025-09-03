package com.vitor.gestaodeiventario.gestao_de_inventario.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitor.gestaodeiventario.gestao_de_inventario.repositorie.usuario.UsuarioRepositorie;

@Service
public class UserDeTailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorie repositorie;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.repositorie.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	}

}
