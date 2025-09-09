package com.vitor.gestaodeiventario.gestao_de_inventario.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {

	@Autowired
	private FiltroSeguranca filtroSeguranca;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(c -> c.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
						.requestMatchers(HttpMethod.PUT, "/usuario/permissoes").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/equipamento/adicionar").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/equipamento/atualizar").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/equipamento/deletar/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/emprestimo/atrasados").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(filtroSeguranca, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
